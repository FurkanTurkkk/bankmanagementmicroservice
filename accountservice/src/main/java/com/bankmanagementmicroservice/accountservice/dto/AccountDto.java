package com.bankmanagementmicroservice.accountservice.dto;

import com.bankmanagementmicroservice.customerservice.dto.CustomerDto;

import java.math.BigDecimal;

public class AccountDto {
    private CustomerDto customerDto;
    private String accountNumber;
    private BigDecimal balance;

    public AccountDto(CustomerDto customerDto, String accountNumber, BigDecimal balance) {
        this.customerDto = customerDto;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public CustomerDto getCustomerDto() {
        return customerDto;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
