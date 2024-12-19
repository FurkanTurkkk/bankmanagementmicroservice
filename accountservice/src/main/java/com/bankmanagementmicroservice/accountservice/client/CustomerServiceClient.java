package com.bankmanagementmicroservice.accountservice.client;

import com.bankmanagementmicroservice.customerservice.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServiceClient {
    @GetMapping("/api/v1/customer/{customerId}")
    ResponseEntity<CustomerDto> findCustomerById(@PathVariable("customerId")Long id);
}
