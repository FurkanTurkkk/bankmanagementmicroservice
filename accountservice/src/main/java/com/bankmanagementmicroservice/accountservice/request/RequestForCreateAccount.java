package com.bankmanagementmicroservice.accountservice.request;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RequestForCreateAccount {
    private Long customerId;
    private BigDecimal balance=BigDecimal.ZERO;

    public RequestForCreateAccount() {
    }

    public RequestForCreateAccount(Long customerId, BigDecimal balance) {
        this.customerId = customerId;
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
