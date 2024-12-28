package com.bankmanagementmicroservice.accountservice;

import com.bankmanagementmicroservice.accountservice.exception.AccountNotFoundException;
import com.bankmanagementmicroservice.accountservice.model.Account;
import com.bankmanagementmicroservice.accountservice.repository.AccountRepository;
import com.bankmanagementmicroservice.accountservice.service.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;


@SpringBootTest
class AccountserviceApplicationTests {

	@Mock
	private AccountRepository accountRepository;

	@InjectMocks
	private AccountService accountService;

	@Test
	void deleteAccountById_shouldDeleteAccount_whenAccountExist(){
		Long accountId=1L;
		Long customerId=1L;
		Account mockAccount = new Account(customerId);

		when(accountRepository.findById(accountId)).thenReturn(Optional.of(mockAccount));

		accountService.deleteAccountById(accountId);

		verify(accountRepository,times(1)).delete(mockAccount);
	}

	@Test
	void deleteAccountById_shouldThrowException_whenAccountDoesNotExist() {
		Long accountId = 1L;

		when(accountRepository.findById(accountId)).thenReturn(Optional.empty());

		assertThrows(AccountNotFoundException.class, () -> accountService.deleteAccountById(accountId));
	}

    @Test
	void contextLoads() {
	}

}
