package com.bankmanagementmicroservice.customerservice.controller;

import com.bankmanagementmicroservice.customerservice.request.RequestForCreateCustomer;
import com.bankmanagementmicroservice.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import org.example.CustomerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createNewCustomer(@Valid @RequestBody RequestForCreateCustomer request){
        return ResponseEntity.ok(customerService.addCustomer(request));
    }

    @PutMapping("/{customerId}/address/{addressId}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("customerId") Long customerId,
                                                      @PathVariable("addressId") Long addressId){
        return ResponseEntity.ok(customerService.updateAddressOfCustomerById(customerId,addressId));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("customerId")Long addressId){
        return ResponseEntity.ok(customerService.getCustomerById(addressId));
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<String> deleteCustomerByCustomerId(@PathVariable("customerId")Long customerId){
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.ok("Customer could deleted successfully");
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAllCustomer(){
        customerService.deleteAllCustomer();
        return ResponseEntity.ok("Customer Repository has not any member");
    }
}
