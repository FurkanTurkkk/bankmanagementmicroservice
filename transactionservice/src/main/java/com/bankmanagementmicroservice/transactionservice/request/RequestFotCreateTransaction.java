package com.bankmanagementmicroservice.transactionservice.request;

import com.bankmanagementmicroservice.transactionservice.model.TransactionType;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class RequestFotCreateTransaction {

    private LocalDate transferDate=LocalDate.now();
    private Long accountId;
    private BigDecimal amount;
    private TransactionType type;

    public RequestFotCreateTransaction() {
    }

    public RequestFotCreateTransaction(Long accountId, BigDecimal amount, TransactionType type) {
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
}
