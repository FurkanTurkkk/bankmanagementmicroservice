package com.bankmanagementmicroservice.transactionservice.dtoconverter;

import com.bankmanagementmicroservice.transactionservice.model.Transaction;
import org.example.TransactionDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionDtoConverter {
    public TransactionDto convert(Transaction transaction){
        return new TransactionDto(
                transaction.getAccountId(),
                transaction.getTransferDate(),
                transaction.getType().toString(),
                transaction.getAmount()
        );
    }
}
