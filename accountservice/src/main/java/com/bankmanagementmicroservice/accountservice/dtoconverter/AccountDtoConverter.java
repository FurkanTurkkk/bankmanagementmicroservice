package com.bankmanagementmicroservice.accountservice.dtoconverter;

import com.bankmanagementmicroservice.accountservice.client.CustomerServiceClient;
import com.bankmanagementmicroservice.accountservice.dto.AccountDto;
import com.bankmanagementmicroservice.accountservice.model.Account;
import com.bankmanagementmicroservice.customerservice.dto.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {
    private final CustomerServiceClient customerServiceClient;

    public AccountDtoConverter(CustomerServiceClient customerServiceClient) {
        this.customerServiceClient = customerServiceClient;
    }

    public AccountDto convert(Account account){
        CustomerDto customerDto=customerServiceClient.findCustomerById(account.getCustomerId()).getBody();
        return new AccountDto(
                customerDto,
                account.getAccountNumber(),
                account.getBalance()
        );
    }
}
