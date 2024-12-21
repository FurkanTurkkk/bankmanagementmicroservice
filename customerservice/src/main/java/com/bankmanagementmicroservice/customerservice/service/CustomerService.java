package com.bankmanagementmicroservice.customerservice.service;


import com.bankmanagementmicroservice.customerservice.client.AddressServiceClient;
import com.bankmanagementmicroservice.customerservice.dtoconverter.CustomerDtoConverter;
import com.bankmanagementmicroservice.customerservice.exception.CustomerExistException;
import com.bankmanagementmicroservice.customerservice.exception.CustomerNotFoundException;
import com.bankmanagementmicroservice.customerservice.model.Customer;
import com.bankmanagementmicroservice.customerservice.repository.CustomerRepository;
import com.bankmanagementmicroservice.customerservice.request.RequestForCreateCustomer;
import jakarta.transaction.Transactional;
import org.example.AccountDto;
import org.example.AddressDto;
import org.example.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressServiceClient addressServiceClient;

    public CustomerService(CustomerRepository customerRepository, AddressServiceClient addressServiceClient) {
        this.customerRepository = customerRepository;
        this.addressServiceClient = addressServiceClient;
    }

    private Customer createNewCustomer(RequestForCreateCustomer request){
        if(customerRepository.findByTcAndAddressId(request.getTc(),request.getAddressId()).isPresent()){
            throw new CustomerExistException("Customer already exist");
        }else {
            Customer customer=new Customer(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getTc(),
                    request.getAddressId(),
                    request.getBirthDay(),
                    request.getPhoneNumber()
            );
            return customerRepository.save(customer);
        }
    }


    private Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer could not found by id : "+id));
    }

    public CustomerDto getCustomerById(Long id){
        Customer customer=findCustomerById(id);
        AddressDto addressDto=addressServiceClient.findAddressByAddressId(customer.getAddressId()).getBody();
        return CustomerDtoConverter.convert(customer,addressDto);
    }

    @Transactional
    public CustomerDto addCustomer(RequestForCreateCustomer request){
        Customer registeredCustomer=createNewCustomer(request);
        AddressDto addressDto=addressServiceClient.findAddressByAddressId(request.getAddressId()).getBody();
        return CustomerDtoConverter.convert(registeredCustomer,addressDto);
    }
}
