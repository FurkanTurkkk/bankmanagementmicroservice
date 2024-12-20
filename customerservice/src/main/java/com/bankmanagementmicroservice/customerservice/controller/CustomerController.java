package com.bankmanagementmicroservice.customerservice.controller;

import com.bankmanagementmicroservice.customerservice.service.CustomerService;
import org.example.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("customerId")Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
}
