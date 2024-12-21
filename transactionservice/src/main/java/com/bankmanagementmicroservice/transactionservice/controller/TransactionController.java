package com.bankmanagementmicroservice.transactionservice.controller;

import com.bankmanagementmicroservice.transactionservice.request.RequestFotCreateTransaction;
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

    @PostMapping
    public ResponseEntity<TransactionDto> addTransaction(@RequestBody RequestFotCreateTransaction request){
        return ResponseEntity.ok(transactionService.addNewTransaction(request));
    }
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDto> getTransaction(@PathVariable("transactionId")Long id){
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }
}
