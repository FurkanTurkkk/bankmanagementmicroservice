package com.bankmanagement.authservice.request;

public record AuthRequest(
        String username,
        String password
) {
}
