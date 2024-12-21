package com.bankmanagementmicroservice.accountservice.exceptionhandler;

import com.bankmanagementmicroservice.accountservice.exception.AccountNotCreatedException;
import feign.FeignException;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.login.AccountNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccountNotCreatedException.class)
    public ResponseEntity<String> handleAccountNotCreatedException(AccountNotCreatedException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException e){
        if(e.status()==404){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Account servisin kullandığı dış serviste kaynak bulunamadı");
        }
        else if (e.status()==400) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Account servisin kullandığı dış servisten geçersiz istek hatası");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Account servisin kullandığı dış serviste beklenmeyen bir hata oluştu");
        }
    }
}
