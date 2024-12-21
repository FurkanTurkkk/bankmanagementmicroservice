package com.bankmanagementmicroservice.transactionservice.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate transferDate=LocalDate.now();

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false)
    private BigDecimal amount;

    private TransactionType type;

    public Transaction() {
    }

    public Transaction(LocalDate transferDate, Long accountId, BigDecimal amount, TransactionType type) {
        this.transferDate = transferDate;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
    }

    public Long getId() {
        return id;
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
