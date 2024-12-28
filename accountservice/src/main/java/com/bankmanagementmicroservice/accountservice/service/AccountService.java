package com.bankmanagementmicroservice.accountservice.service;

import com.bankmanagementmicroservice.accountservice.dtoconverter.AccountDtoConverter;
import com.bankmanagementmicroservice.accountservice.exception.AccountNotCreatedException;
import com.bankmanagementmicroservice.accountservice.exception.AccountNotFoundException;
import com.bankmanagementmicroservice.accountservice.feignclient.CustomerServerFeignClient;
import com.bankmanagementmicroservice.accountservice.model.Account;
import com.bankmanagementmicroservice.accountservice.repository.AccountRepository;
import com.bankmanagementmicroservice.accountservice.request.RequestDecreaseBalance;
import com.bankmanagementmicroservice.accountservice.request.RequestForCreateAccount;
import jakarta.transaction.Transactional;
import org.example.AccountDto;
import org.example.CustomerDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        return accountRepository.findById(id)
                .orElseThrow(()->new AccountNotFoundException("Account could not found by id : "+id));

    }

    private Account findAccountByAccountNumber(String accountNumber){
        try{
            return accountRepository.findByAccountNumber(accountNumber)
                    .orElseThrow(()->new AccountNotFoundException("Bu hesap numarasına ait hesap bulunamadı"+accountNumber));
        }catch (AccountNotFoundException e){
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

    @Transactional
    public void deleteAccountById(Long id){
        Account account=findAccountById(id);
        accountRepository.delete(account);
    }

    @Transactional
    public AccountDto increaseBalanceOfAccountById(Long id, BigDecimal amount){
        Account account=findAccountById(id);
        CustomerDto customerDto=customerServerFeignClient.findCustomerById(account.getCustomerId()).getBody();
        account.increaseBalance(amount);
        updateAccount(account);
        return converter.convert(account,customerDto);
    }

    @Transactional
    public AccountDto decreaseBalanceOfAccountById(RequestDecreaseBalance request){
        logger.info("Bakiye düşürme metoduna giriş yapıldı");
        Account fromAccount=findAccountByAccountNumber(request.getFromAccountNumber());
        logger.info("Gönderecek hesap bulundu");
        Account toAccount=findAccountByAccountNumber(request.getToAccountNumber());
        logger.info("Gönderilen hesap bulundu");
        CustomerDto fromCustomerDto=customerServerFeignClient.findCustomerById(fromAccount.getCustomerId()).getBody();
        logger.info("Gönderen hesabın müşterisi bulundu");
        customerServerFeignClient.findCustomerById(toAccount.getCustomerId());
        logger.info("Gönderilecek hesabın müşterisi bulundu");
        logger.info("Account servis sınıfında hesap bakiyesi yükseltilecek hesap bulundu");
        fromAccount.decreaseBalance(request.getAmount());
        toAccount.increaseBalance(request.getAmount());
        logger.info("Account servis sınıfında accountun bakiyesi yükseltildi");
        updateAccount(fromAccount);
        updateAccount(toAccount);
        logger.info("Account servis sınıfında account nesnesi veri tabanında güncellendi");
        return converter.convert(fromAccount,fromCustomerDto);
    }

}
