package com.sarvika.orderservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sarvika.orderservice.entity.JwtResponse;
import com.sarvika.orderservice.entity.LoginRequest;

@Component
public class TokenProvider {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${auth.service.username}")
	private String username;

	@Value("${auth.service.password}")
	private String password;

	@Value("${api.gateway.auth.service.url}")
	private String authServiceUrl;

	public String getToken() {
	    // Prepare the URL with query parameters
	    String url = String.format("%s?username=%s&password=%s", authServiceUrl, username, password);
	    
	    // Prepare headers
	    HttpHeaders headers = new HttpHeaders();
	    headers.set("Content-Type", "application/json");
	    
	    // Prepare the request entity without body
	    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
	    
	    // Send POST request to authentication service with query parameters
	    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
	    
	    // Extract the token from the response
	    if (responseEntity.getStatusCode().is2xxSuccessful()) {
	        return responseEntity.getBody().toString(); // Assuming JwtResponse has a getToken() method
	    } else {
	        throw new RuntimeException("Failed to retrieve token from auth service");
	    }
	}
}
