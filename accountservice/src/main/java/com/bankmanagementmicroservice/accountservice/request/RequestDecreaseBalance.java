package com.bankmanagementmicroservice.accountservice.request;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class RequestDecreaseBalance {

    private String fromAccountNumber;
    private String toAccountNumber;
    private BigDecimal amount;

    public RequestDecreaseBalance() {
    }

    public RequestDecreaseBalance(String fromAccountNumber,String toAccountNumber,BigDecimal amount) {
        this.fromAccountNumber=fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount=amount;
    }

    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }


    public BigDecimal getAmount() {
        return amount;
    }

}
