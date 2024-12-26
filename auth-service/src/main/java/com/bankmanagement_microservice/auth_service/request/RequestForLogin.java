package com.bankmanagement_microservice.auth_service.request;

import org.springframework.stereotype.Component;

@Component
public class RequestForLogin{
    private String username;
    private String password;

    public RequestForLogin() {
    }

    public RequestForLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
