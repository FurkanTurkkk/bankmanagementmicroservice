package com.bankmanagementmicroservice.adressservice.controller;

import com.bankmanagementmicroservice.adressservice.request.RequestForCreateAddress;
import com.bankmanagementmicroservice.adressservice.service.AddressService;
import org.example.AddressDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity<AddressDto> createNewAddress(@RequestBody RequestForCreateAddress request){
        return ResponseEntity.ok(addressService.addAddress(request));
    }

    @GetMapping("/id/{addressId}")
    public ResponseEntity<AddressDto> findAddressByAddressId(@PathVariable("addressId")Long id){
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> deleteAddressByAddressId(@PathVariable("addressId")Long id){
        return ResponseEntity.ok("Address could delete successfully by id : "+id);
    }
}
