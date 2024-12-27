package com.bankmanagementmicroservice.adressservice.exception;

public class AddressAlreadyExistException extends RuntimeException {
    public AddressAlreadyExistException(String s) {
        super(s);
    }
}
