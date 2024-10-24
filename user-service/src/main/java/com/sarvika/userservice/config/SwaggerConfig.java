package com.sarvika.userservice.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;


@Configuration
public class SwaggerConfig {


	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().info(new Info().title("User service").version("1.0")
				.description("This is User service API documentation"));
	}


	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("public").pathsToMatch("/**").build();
	}
}