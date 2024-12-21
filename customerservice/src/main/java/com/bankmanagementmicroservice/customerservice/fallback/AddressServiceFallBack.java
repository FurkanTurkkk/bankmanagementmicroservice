package com.bankmanagementmicroservice.customerservice.fallback;

import com.bankmanagementmicroservice.customerservice.client.AddressServiceClient;
import org.example.AccountDto;
import org.example.AddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressServiceFallBack implements AddressServiceClient {
    @Override
    public ResponseEntity<AddressDto> findAddressByAddressId(Long id) {
        return ResponseEntity.ok(new AddressDto("","",0L,0L));
    }
}
