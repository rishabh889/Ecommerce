package com.sarvika.authenticationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sarvika.authenticationservice.entity.User;
import com.sarvika.authenticationservice.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Register a new user", description = "Registers a new user with the provided details.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User successfully registered", 
                     content = { @Content(mediaType = "application/json", 
                     schema = @Schema(implementation = User.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input", 
                     content = @Content)
    })
    @PostMapping("/register")
    public User register(
        @RequestBody User user) {  // Use Spring's @RequestBody
        return userService.saveUser(user);
    }

    @Operation(summary = "Authenticate a user", description = "Authenticates a user with the provided username and password.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User successfully authenticated", 
                     content = @Content),
        @ApiResponse(responseCode = "401", description = "Invalid username or password", 
                     content = @Content)
    })
    @PostMapping("/authenticate")
    public String authenticate(
        @RequestParam String username, 
        @RequestParam String password) {
        return userService.authenticate(username, password);
    }
}
