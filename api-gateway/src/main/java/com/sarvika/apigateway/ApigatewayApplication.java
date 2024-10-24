package com.sarvika.apigateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import com.sarvika.apigateway.filter.JWTValidationFilter;

@SpringBootApplication
@EnableDiscoveryClient
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

	@Autowired
	private JWTValidationFilter jwtValidationFilter;

	@Bean
	public RouteLocator apiRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("order_route",
						route -> route.path("/api/orders/**").filters(temp -> temp.filter(jwtValidationFilter))
								.uri("lb://order-service"))
				.route("product_route",
						route -> route.path("/api/products/**").filters(temp -> temp.filter(jwtValidationFilter))
								.uri("lb://product-service"))
				.route("user_route",
						route -> route.path("/api/users/**").filters(temp -> temp.filter(jwtValidationFilter))
								.uri("lb://user-service"))
				.route("authentication_route", route -> route.path("/api/auth/**").uri("lb://authentication-service"))
				.build();

	}

}
