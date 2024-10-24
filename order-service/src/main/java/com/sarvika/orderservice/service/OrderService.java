package com.sarvika.orderservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sarvika.orderservice.dao.OrderRepository;
import com.sarvika.orderservice.entity.Order;
import com.sarvika.orderservice.entity.ProductDTO;
import com.sarvika.orderservice.entity.UserDTO;
import com.sarvika.orderservice.exception.ResourceNotFoundException;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private TokenProvider tokenProvider;

    @Value("${api.gateway.user.service.url}")
    private String userServiceUrl;

    @Value("${api.gateway.product.service.url}")
    private String productServiceUrl;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order createOrder(Order order) {
        // Fetch the token to call other services
        String token = tokenProvider.getToken();

        // Prepare headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Check user existence
        HttpEntity<Void> userRequestEntity = new HttpEntity<>(headers);
        UserDTO user = fetchUser(order.getUserId(), userRequestEntity);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }

        // Check product availability
        HttpEntity<Void> productRequestEntity = new HttpEntity<>(headers);
        ProductDTO product = fetchProduct(order.getProductId(), productRequestEntity);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }

        // Save the order after validating user and product
        return orderRepository.save(order);
    }

    private UserDTO fetchUser(Long userId, HttpEntity<Void> requestEntity) {
        try {
            ResponseEntity<UserDTO> userResponse = restTemplate.exchange(
                    userServiceUrl + "/" + userId,
                    HttpMethod.GET,
                    requestEntity,
                    UserDTO.class
            );

            if (userResponse.getStatusCode().is2xxSuccessful()) {
                return userResponse.getBody();
            } else {
                throw new ResourceNotFoundException("User not found for ID: " + userId);
            }
        } catch (RestClientException e) {
            // Log the error and throw an appropriate exception
            e.printStackTrace();
            throw new ResourceNotFoundException("Error occurred while fetching user: " + e.getMessage());
        }
    }

    private ProductDTO fetchProduct(String string, HttpEntity<Void> requestEntity) {
        try {
            ResponseEntity<ProductDTO> productResponse = restTemplate.exchange(
                    productServiceUrl + "/" + string,
                    HttpMethod.GET,
                    requestEntity,
                    ProductDTO.class
            );

            if (productResponse.getStatusCode().is2xxSuccessful()) {
                return productResponse.getBody();
            } else {
                throw new ResourceNotFoundException("Product not found for ID: " + string);
            }
        } catch (RestClientException e) {
            // Log the error and throw an appropriate exception
            e.printStackTrace();
            throw new ResourceNotFoundException("Error occurred while fetching product: " + e.getMessage());
        }
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
