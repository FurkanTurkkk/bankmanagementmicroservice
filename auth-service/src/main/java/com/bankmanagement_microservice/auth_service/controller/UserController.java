package com.bankmanagement_microservice.auth_service.controller;

import com.bankmanagement_microservice.auth_service.config.PasswordEncoderConfig;
import com.bankmanagement_microservice.auth_service.model.User;
import com.bankmanagement_microservice.auth_service.request.RequestForCreateUser;
import com.bankmanagement_microservice.auth_service.request.RequestForLogin;
import com.bankmanagement_microservice.auth_service.service.UserService;
import com.bankmanagement_microservice.auth_service.utility.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    public UserController(UserService userService, PasswordEncoderConfig passwordEncoderConfig, PasswordEncoder passwordEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }


    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody RequestForCreateUser request){
        userService.createNewUser(request);
        return ResponseEntity.ok("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody RequestForLogin request){
        User user=userService.findUserByUsername(request.getUsername());
        if(user==null || !passwordEncoder.matches(request.getPassword(),user.getPassword())){
            return ResponseEntity.status(401).body("Invalid username or password");
        }
        String token = jwtTokenUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(token);
    }
    @PostMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestBody String token) {
        try {
            String username = jwtTokenUtil.getUsernameFromToken(token);
            if (username != null && userService.findUserByUsername(username) != null) {
                return ResponseEntity.ok("Token is valid");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token");
        }
        return ResponseEntity.status(401).body("Invalid token");
    }

}
