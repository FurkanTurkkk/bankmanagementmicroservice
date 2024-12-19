package com.bankmanagementmicroservice.customerservice.service;

import com.bankmanagementmicroservice.customerservice.dto.CustomerDto;
import com.bankmanagementmicroservice.customerservice.dtoconverter.CustomerDtoConverter;
import com.bankmanagementmicroservice.customerservice.exception.CustomerNotFoundException;
import com.bankmanagementmicroservice.customerservice.model.Customer;
import com.bankmanagementmicroservice.customerservice.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    private Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer could not found by id : "+id));
    }

    public CustomerDto getCustomerById(Long id){
        return converter.convert(findCustomerById(id));
    }
}
