package com.bankmanagement.authservice.controller;

import com.bankmanagement.authservice.request.AuthRequest;
import com.bankmanagement.authservice.request.CreateUserRequest;
import com.bankmanagement.authservice.service.JwtService;
import com.bankmanagement.authservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public String addUser(@RequestBody CreateUserRequest request){
        userService.createUser(request);
        return "Kayıt başarılı";
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest request){
        Authentication authentication= authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.username(),request.password()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(request.username());
        }
        throw new UsernameNotFoundException("Invalid username {}"+request.username());
    }
}
