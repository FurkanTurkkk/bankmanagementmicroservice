package com.bankmanagementmicroservice.accountservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Random;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private final String accountNumber=generateRandomAccountNumber(10);
    private BigDecimal balance;

    public Account() {
    }

    public Account(Long customerId) {
        this.customerId = customerId;
        this.balance = BigDecimal.ZERO;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private String generateRandomAccountNumber(int length){
        Random random=new Random();
        StringBuilder accountNumberBuilder=new StringBuilder();

        for (int i=0;i<length;i++){
            accountNumberBuilder.append(random.nextInt(10));
        }

        return accountNumberBuilder.toString();
    }

    protected void increaseBalance(BigDecimal amount){
        this.balance=balance.add(amount);
    }
    protected void decreaseBalance(BigDecimal amount){
        if(balance.compareTo(amount) >= 0){
            this.balance=balance.subtract(amount);
        }
        else {
            throw new IllegalArgumentException("Yetersiz Bakiye ! ");
        }
    }
}
