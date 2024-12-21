package com.bankmanagementmicroservice.customerservice.controller;

import com.bankmanagementmicroservice.customerservice.request.RequestForCreateCustomer;
import com.bankmanagementmicroservice.customerservice.service.CustomerService;
import org.example.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@RequestBody RequestForCreateCustomer request){
        return ResponseEntity.ok(customerService.addCustomer(request));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("customerId")Long id){
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }
}
