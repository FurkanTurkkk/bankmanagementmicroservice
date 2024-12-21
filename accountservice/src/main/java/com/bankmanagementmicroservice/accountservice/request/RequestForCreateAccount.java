package com.bankmanagementmicroservice.accountservice.request;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RequestForCreateAccount {
    private Long customerId;
    private BigDecimal balance;

    public RequestForCreateAccount() {
    }

    public RequestForCreateAccount(Long customerId) {
        this.customerId = customerId;
        this.balance = BigDecimal.ZERO;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
