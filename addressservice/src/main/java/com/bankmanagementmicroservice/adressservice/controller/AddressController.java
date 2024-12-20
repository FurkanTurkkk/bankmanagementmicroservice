package com.bankmanagementmicroservice.adressservice.controller;

import com.bankmanagementmicroservice.adressservice.service.AddressService;
import org.example.AddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/id/{addressId}")
    public ResponseEntity<AddressDto> findAddressByAddressId(@PathVariable("addressId")Long id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }
}
