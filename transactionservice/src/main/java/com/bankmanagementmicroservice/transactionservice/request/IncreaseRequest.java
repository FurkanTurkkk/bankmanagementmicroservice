package com.bankmanagementmicroservice.transactionservice.request;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class IncreaseRequest {
    private Long accountId;
    private BigDecimal amount;
    private final LocalDate transactionDate;

    public IncreaseRequest() {
        this.transactionDate=LocalDate.now();
    }

    public IncreaseRequest(Long accountId,BigDecimal amount) {
        this.accountId=accountId;
        this.amount = amount;
        this.transactionDate=LocalDate.now();
    }

    public Long getAccountId() {
        return accountId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
