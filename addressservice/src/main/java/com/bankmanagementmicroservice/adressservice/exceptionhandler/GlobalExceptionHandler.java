package com.bankmanagementmicroservice.adressservice.exceptionhandler;

import com.bankmanagementmicroservice.adressservice.exception.AddressAlreadyExistException;
import com.bankmanagementmicroservice.adressservice.exception.AddressNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<String> handleAddressNotFoundException(AddressNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AddressAlreadyExistException.class)
    public ResponseEntity<String> handleAddressAlreadyExistException(AddressAlreadyExistException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
