package com.bankmanagementmicroservice.customerservice.fallback;

import com.bankmanagementmicroservice.customerservice.feignclient.AddressServiceFeignClient;
import org.example.AddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressServiceFeignFallBack implements AddressServiceFeignClient {
    @Override
    public ResponseEntity<AddressDto> findAddressByAddressId(Long id) {
        return ResponseEntity.ok(new AddressDto("","","","",0L,0L));
    }
}
