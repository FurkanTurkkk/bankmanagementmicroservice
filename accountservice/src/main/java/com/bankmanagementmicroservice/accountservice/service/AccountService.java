package com.bankmanagementmicroservice.accountservice.service;

import com.bankmanagementmicroservice.accountservice.dto.AccountDto;
import com.bankmanagementmicroservice.accountservice.dtoconverter.AccountDtoConverter;
import com.bankmanagementmicroservice.accountservice.model.Account;
import com.bankmanagementmicroservice.accountservice.repository.AccountRepository;
import com.bankmanagementmicroservice.accountservice.request.RequestForCreateAccount;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoConverter converter;

    public AccountService(AccountRepository accountRepository, AccountDtoConverter converter) {
        this.accountRepository = accountRepository;
        this.converter = converter;
    }

    private Account createNewAccount(Long customerId){
        return accountRepository.save(new Account(customerId));
    }

    private Account findAccountById(Long id){
        try {
            return accountRepository.findById(id)
                    .orElseThrow(()->new AccountNotFoundException("Account could not found by id : "+id));
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public AccountDto addNewAccount(RequestForCreateAccount request){
        Account account=createNewAccount(request.getCustomerId());
        return converter.convert(account);
    }

    public AccountDto getAccountById(Long id){
        Account account=findAccountById(id);
        return converter.convert(account);
    }
}
