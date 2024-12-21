package com.bankmanagementmicroservice.customerservice.exception;

public class CustomerExistException extends RuntimeException {
    public CustomerExistException(String customerAlreadyExist) {
    }
}
