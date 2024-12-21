package com.bankmanagementmicroservice.accountservice.feignclient;

import org.example.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service")
public interface CustomerServerFeignClient {
    @GetMapping("/api/v1/customer/{customerId}")
    public ResponseEntity<CustomerDto> findCustomerById(@PathVariable("customerId")Long id);
}
