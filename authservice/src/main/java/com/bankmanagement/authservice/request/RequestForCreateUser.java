package com.bankmanagement.authservice.request;

import com.bankmanagement.authservice.validation.ValidName;
import com.bankmanagement.authservice.validation.ValidPassword;
import jakarta.persistence.Column;
import org.springframework.stereotype.Component;

@Component
public class RequestForCreateUser {

    @Column(unique = true,nullable = false)
    @ValidName
    private String userName;

    @Column(nullable = false)
    @ValidPassword
    private String password;

    private String role;

    public RequestForCreateUser() {
    }

    public RequestForCreateUser(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
