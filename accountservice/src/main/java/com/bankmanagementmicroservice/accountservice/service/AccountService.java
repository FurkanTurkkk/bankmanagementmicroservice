package com.bankmanagementmicroservice.accountservice.service;

import com.bankmanagementmicroservice.accountservice.dtoconverter.AccountDtoConverter;
import com.bankmanagementmicroservice.accountservice.exception.AccountNotCreatedException;
import com.bankmanagementmicroservice.accountservice.feignclient.CustomerServerFeignClient;
import com.bankmanagementmicroservice.accountservice.model.Account;
import com.bankmanagementmicroservice.accountservice.repository.AccountRepository;
import com.bankmanagementmicroservice.accountservice.request.RequestForCreateAccount;
import feign.FeignException;
import jakarta.transaction.Transactional;
import org.example.AccountDto;
import org.example.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountDtoConverter converter;
    private final CustomerServerFeignClient customerServerFeignClient;

    private static final Logger logger= LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountRepository accountRepository,
                          AccountDtoConverter converter, CustomerServerFeignClient customerServerFeignClient) {
        this.accountRepository = accountRepository;
        this.converter = converter;
        this.customerServerFeignClient = customerServerFeignClient;
    }


    private Account createNewAccount(RequestForCreateAccount request){
        logger.info("createNewAccount metoda giriş yapıldı");
        Long customerId= request.getCustomerId();
        if(customerServerFeignClient.findCustomerById(customerId).getBody()!=null){
            return accountRepository.save(new Account(customerId));
        }
        throw new AccountNotCreatedException("Dış kaynaktan boş veri geldi account oluşturulamadı ! ");
    }

    private List<Account> findAccountByCustomerId(Long customerId){
        logger.info("findAccountByCustomerId metoduna giriş yapıldı");
        return accountRepository.findByCustomerId(customerId)
                .orElse(new ArrayList<>());
    }
    private Account findAccountById(Long id){
        try {
            return accountRepository.findById(id)
                    .orElseThrow(()->new AccountNotFoundException("Account could not found by id : "+id));
        } catch (AccountNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void updateAccount(Account account){
        accountRepository.save(account);
    }

    @Transactional
    public AccountDto addNewAccount(RequestForCreateAccount request){
        Account account=createNewAccount(request);
        CustomerDto customerDto=customerServerFeignClient.findCustomerById(request.getCustomerId()).getBody();
        return converter.convert(account,customerDto);
    }

    @Transactional
    public AccountDto getAccountById(Long id){
        Account account=findAccountById(id);
        CustomerDto customerDto=customerServerFeignClient.findCustomerById(account.getCustomerId()).getBody();
        return converter.convert(account,customerDto);
    }

    @Transactional
    public List<AccountDto> getAllAccount(Long customerId){
        CustomerDto customerDto=customerServerFeignClient.findCustomerById(customerId).getBody();
        return findAccountByCustomerId(customerId).stream()
                .map(t->converter.convert(t,customerDto)).toList();
    }

    public void deleteAccountById(Long id){
        Account account=findAccountById(id);
        accountRepository.delete(account);
    }

    public AccountDto increaseBalanceOfAccountById(Long id, BigDecimal amount){
        Account account=findAccountById(id);
        CustomerDto customerDto=customerServerFeignClient.findCustomerById(account.getCustomerId()).getBody();
        account.increaseBalance(amount);
        updateAccount(account);
        return converter.convert(account,customerDto);
    }

    public AccountDto decreaseBalanceOfAccountById(Long id,BigDecimal amount){
        Account account=findAccountById(id);
        CustomerDto customerDto=customerServerFeignClient.findCustomerById(account.getCustomerId()).getBody();
        logger.info("Account servis sınıfında hesap bakiyesi yükseltilecek hesap bulundu");
        account.decreaseBalance(amount);
        logger.info("Account servis sınıfında accountun bakiyesi yükseltildi");
        updateAccount(account);
        logger.info("Account servis sınıfında account nesnesi veri tabanında güncellendi");
        return converter.convert(account,customerDto);
    }

}
