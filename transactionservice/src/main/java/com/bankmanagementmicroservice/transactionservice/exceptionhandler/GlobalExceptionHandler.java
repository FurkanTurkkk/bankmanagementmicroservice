package com.bankmanagementmicroservice.transactionservice.exceptionhandler;

import com.bankmanagementmicroservice.transactionservice.exception.TransactionNotFoundException;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<String> handleTransactionNotFoundException(TransactionNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException e){
        if(e.status()==404){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Transaction servisin kullandığı dış serviste kaynak bulunamadı");
        }
        else if (e.status()==400) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Transaction servisin kullandığı dış servisten geçersiz istek hatası");
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Transaction servisin kullandığı dış serviste beklenmeyen bir hata oluştu");
        }
    }
}
