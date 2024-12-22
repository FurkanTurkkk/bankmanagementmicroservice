package com.bankmanagementmicroservice.accountservice.controller;

import com.bankmanagementmicroservice.accountservice.request.RequestDecreaseBalance;
import com.bankmanagementmicroservice.accountservice.request.RequestForCreateAccount;
import com.bankmanagementmicroservice.accountservice.service.AccountService;
import org.example.AccountDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

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

    @PutMapping("/increase/to-account/{accountId}")
    public ResponseEntity<AccountDto> increaseAccountBalance(@PathVariable("accountId") Long id,
                                                            @RequestBody BigDecimal amount){
        return ResponseEntity.ok(accountService.increaseBalanceOfAccountById(id,amount));
    }

    @PutMapping("/decrease")
    public ResponseEntity<AccountDto> decreaseAccountBalance(@RequestBody RequestDecreaseBalance request){
        return ResponseEntity.ok(accountService.decreaseBalanceOfAccountById(request));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDto> getAccountByAccountId(@PathVariable("accountId")Long id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping("/all-account-customerId/{customerId}")
    public ResponseEntity<List<AccountDto>> getAllAccountByCustomerId(@PathVariable("customerId")Long customerId){
        return ResponseEntity.ok(accountService.getAllAccount(customerId));
    }


    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccountById(@PathVariable("accountId")Long id){
        accountService.deleteAccountById(id);
        return ResponseEntity.ok("Hesap başarıyla silindi.");
    }
}
