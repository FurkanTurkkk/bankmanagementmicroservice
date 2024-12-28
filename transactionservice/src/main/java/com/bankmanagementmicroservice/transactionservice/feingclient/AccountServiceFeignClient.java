package com.bankmanagementmicroservice.transactionservice.feingclient;

import com.bankmanagementmicroservice.transactionservice.request.RequestDecreaseBalance;
import org.example.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@FeignClient(name = "account-service")
public interface AccountServiceFeignClient {

    @PutMapping("/api/v1/account/decrease")
    public ResponseEntity<AccountDto> decreaseAccountBalance(@RequestBody RequestDecreaseBalance request);

    @PutMapping("/api/v1/account/increase/to-account/{accountId}")
    ResponseEntity<AccountDto> increaseAccountBalance(@PathVariable("accountId") Long accountId,
                                                             @RequestBody BigDecimal amount);

    @GetMapping("/api/v1/account/{accountId}")
    ResponseEntity<AccountDto> getAccountByAccountId(@PathVariable("accountId")Long accountId);

}
