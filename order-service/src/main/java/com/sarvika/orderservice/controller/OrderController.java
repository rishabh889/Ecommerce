package com.sarvika.orderservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sarvika.orderservice.entity.Order;
import com.sarvika.orderservice.entity.ProductDTO;
import com.sarvika.orderservice.entity.UserDTO;
import com.sarvika.orderservice.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;


	@Operation(summary = "Get all orders", description = "Retrieve a list of all orders.")
	@GetMapping
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();
	}

	@Operation(summary = "Get order by ID", description = "Retrieve an order by its unique ID.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Order found"),
			@ApiResponse(responseCode = "404", description = "Order not found") })
	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Order order = orderService.getOrderById(id);
		return order != null ? ResponseEntity.ok(order) : ResponseEntity.notFound().build();
	}

	@Operation(summary = "Create a new order", description = "Create a new order in the system.")
	@ApiResponse(responseCode = "201", description = "Order created")
	@PostMapping
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		
		Order createdOrder = orderService.createOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
	}

	@Operation(summary = "Delete order", description = "Delete an order by its unique ID.")
	@ApiResponse(responseCode = "204", description = "Order deleted")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		orderService.deleteOrder(id);
		return ResponseEntity.noContent().build();
	}
}
