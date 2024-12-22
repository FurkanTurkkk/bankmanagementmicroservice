package com.bankmanagementmicroservice.transactionservice.controller;

import com.bankmanagementmicroservice.transactionservice.model.TransactionType;
import com.bankmanagementmicroservice.transactionservice.request.IncreaseRequest;
import com.bankmanagementmicroservice.transactionservice.request.RequestDecreaseBalance;
import com.bankmanagementmicroservice.transactionservice.service.TransactionService;
import org.example.TransactionDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/increase/{transactionType}")
    public ResponseEntity<TransactionDto> increaseBalance(@PathVariable("transactionType")TransactionType type,
                                                          @RequestBody IncreaseRequest request){
        return ResponseEntity.ok(transactionService.increaseBalanceOfAccount(type,request));
    }

    @PostMapping("/decrease/{transactionType}")
    public ResponseEntity<TransactionDto> decreaseBalance(@PathVariable("transactionType")TransactionType type,
                                                          @RequestBody RequestDecreaseBalance request){
        return ResponseEntity.ok(transactionService.decreaseBalanceOfAccount(type,request));
    }

}
