package com.bankmanagement.authservice.controller;

import com.bankmanagement.authservice.request.RequestForCreateUser;
import com.bankmanagement.authservice.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RequestForCreateUser request){
        authService.registerUser(request);
        return ResponseEntity.ok("Registered user successfully");
    }

    @PostMapping("/login")
    public String login(@RequestBody RequestForCreateUser request){
        return authService.validateUser(request);
    }
}
