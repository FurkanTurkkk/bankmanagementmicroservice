package com.bankmanagementmicroservice.transactionservice.service;

import com.bankmanagementmicroservice.transactionservice.dtoconverter.TransactionDtoConverter;
import com.bankmanagementmicroservice.transactionservice.feingclient.AccountServiceFeignClient;
import com.bankmanagementmicroservice.transactionservice.model.Transaction;
import com.bankmanagementmicroservice.transactionservice.model.TransactionType;
import com.bankmanagementmicroservice.transactionservice.repository.TransactionRepository;
import com.bankmanagementmicroservice.transactionservice.request.IncreaseRequest;
import com.bankmanagementmicroservice.transactionservice.request.RequestDecreaseBalance;
import jakarta.transaction.Transactional;
import org.example.AccountDto;
import org.example.TransactionDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionDtoConverter converter;
    private final AccountServiceFeignClient accountServiceFeignClient;

    public TransactionService(TransactionRepository transactionRepository,
                              TransactionDtoConverter converter,
                              AccountServiceFeignClient accountServiceFeignClient) {
        this.transactionRepository = transactionRepository;
        this.converter = converter;
        this.accountServiceFeignClient = accountServiceFeignClient;
    }

    private Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }

    @Transactional
    public TransactionDto increaseBalanceOfAccount(TransactionType type, IncreaseRequest request){
        accountServiceFeignClient.getAccountByAccountId(request.getAccountId()).getBody();
        accountServiceFeignClient.increaseAccountBalance(request.getAccountId(), request.getAmount());
        Transaction transaction=createTransaction(new Transaction(request.getTransactionDate(),
                request.getAccountId(),
                request.getAmount(),
                type));
        return converter.convert(transaction);
    }

    @Transactional
    public TransactionDto decreaseBalanceOfAccount(TransactionType type, RequestDecreaseBalance request){
        accountServiceFeignClient.decreaseAccountBalance(request);
        AccountDto accountDto=accountServiceFeignClient.getAccountByAccountNumber(request.getFromAccountNumber()).getBody();
        Transaction transaction=createTransaction(new Transaction(LocalDate.now(),
                accountDto.getAccountId(),
                request.getAmount(),
                type));
        createTransaction(transaction);
        return converter.convert(transaction);
    }


}
