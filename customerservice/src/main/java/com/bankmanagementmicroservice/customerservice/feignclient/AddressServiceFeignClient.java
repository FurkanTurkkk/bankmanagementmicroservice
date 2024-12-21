package com.bankmanagementmicroservice.customerservice.feignclient;

import org.example.AddressDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "address-service")
public interface AddressServiceFeignClient {
    @GetMapping("/api/v1/address/id/{addressId}")
    ResponseEntity<AddressDto> findAddressByAddressId(@PathVariable("addressId")Long id);
}
