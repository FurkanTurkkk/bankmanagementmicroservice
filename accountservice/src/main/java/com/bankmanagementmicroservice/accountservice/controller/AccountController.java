package com.bankmanagementmicroservice.accountservice.controller;

import com.bankmanagementmicroservice.accountservice.dto.AccountDto;
import com.bankmanagementmicroservice.accountservice.request.RequestForCreateAccount;
import com.bankmanagementmicroservice.accountservice.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody RequestForCreateAccount request){
        return ResponseEntity.ok(accountService.addNewAccount(request));
    }

    @GetMapping("account/{accountId}")
    public ResponseEntity<AccountDto> getAccountByAccountId(@PathVariable("accountId")Long id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }
}
