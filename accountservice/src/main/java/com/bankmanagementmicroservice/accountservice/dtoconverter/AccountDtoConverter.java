package com.bankmanagementmicroservice.accountservice.dtoconverter;

import com.bankmanagementmicroservice.accountservice.model.Account;
import org.example.AccountDto;
import org.example.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {
    private static final Logger log= LoggerFactory.getLogger(AccountDtoConverter.class);

    public AccountDto convert(Account account, CustomerDto customerDto){
        log.info("Account dto işleniyor");
        AccountDto accountDto=new AccountDto(
                customerDto,
                account.getAccountNumber(),
                account.getBalance()
        );
        accountDto.setAccountId(account.getId());
        return accountDto;
    }
}
