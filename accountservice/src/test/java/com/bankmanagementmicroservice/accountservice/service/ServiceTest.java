package com.bankmanagementmicroservice.accountservice.service;

import com.bankmanagementmicroservice.accountservice.dtoconverter.AccountDtoConverter;
import com.bankmanagementmicroservice.accountservice.feignclient.CustomerServerFeignClient;
import com.bankmanagementmicroservice.accountservice.model.Account;
import com.bankmanagementmicroservice.accountservice.repository.AccountRepository;
import com.bankmanagementmicroservice.accountservice.request.RequestForCreateAccount;
import org.checkerframework.checker.units.qual.A;
import org.example.AccountDto;
import org.example.AddressDto;
import org.example.CustomerDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {


}
