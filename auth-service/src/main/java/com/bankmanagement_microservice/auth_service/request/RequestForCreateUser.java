package com.bankmanagement_microservice.auth_service.request;

import org.springframework.stereotype.Component;

@Component
public class RequestForCreateUser {

    private String name;
    private String username;
    private String password;
    private String email;

    public RequestForCreateUser() {
    }

    public RequestForCreateUser(String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
