package com.bankmanagementmicroservice.transactionservice.service;

import com.bankmanagementmicroservice.transactionservice.dtoconverter.TransactionDtoConverter;
import com.bankmanagementmicroservice.transactionservice.exception.TransactionNotFoundException;
import com.bankmanagementmicroservice.transactionservice.model.Transaction;
import com.bankmanagementmicroservice.transactionservice.repository.TransactionRepository;
import com.bankmanagementmicroservice.transactionservice.request.RequestFotCreateTransaction;
import org.example.TransactionDto;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDtoConverter converter;

    public TransactionService(TransactionRepository transactionRepository, TransactionDtoConverter converter) {
        this.transactionRepository = transactionRepository;
        this.converter = converter;
    }

    private Transaction createNewTransaction(RequestFotCreateTransaction request){
        Transaction transaction=new Transaction(
                request.getTransferDate(),
                request.getAccountId(),
                request.getAmount(),
                request.getType()
        );
        return transactionRepository.save(transaction);
    }

    private Transaction findTransactionById(Long id){
        return transactionRepository.findById(id)
                .orElseThrow(()->new TransactionNotFoundException("Transaction could not by id : "+id));
    }

    public TransactionDto addNewTransaction(RequestFotCreateTransaction request){
        return converter.convert(createNewTransaction(request));
    }

    public TransactionDto getTransactionById(Long id){
        return converter.convert(findTransactionById(id));
    }

}
