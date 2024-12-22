package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionDto {

    private Long accountId;
    private LocalDate transactionDate;
    private String type;
    private BigDecimal amount;

    public TransactionDto() {
    }

    public TransactionDto(Long accountId,LocalDate transactionDate,String type,BigDecimal amount) {
        this.accountId = accountId;
        this.transactionDate = transactionDate;
        this.type=type;
        this.amount=amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public Long getAccountId() {
        return accountId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
