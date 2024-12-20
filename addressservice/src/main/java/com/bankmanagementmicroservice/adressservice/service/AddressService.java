package com.bankmanagementmicroservice.adressservice.service;

import com.bankmanagementmicroservice.adressservice.dtoconverter.AddressDtoConverter;
import com.bankmanagementmicroservice.adressservice.exception.AddressNotFoundException;
import com.bankmanagementmicroservice.adressservice.model.Address;
import com.bankmanagementmicroservice.adressservice.repository.AddressRepository;
import org.example.AddressDto;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoConverter converter;

    public AddressService(AddressRepository addressRepository, AddressDtoConverter converter) {
        this.addressRepository = addressRepository;
        this.converter = converter;
    }

    private Address findAddressById(Long id){
        return addressRepository.findById(id)
                .orElseThrow(()->new AddressNotFoundException("Address could not found by id : "+id));
    }

    public AddressDto getAddressById(Long id){
        return converter.convert(findAddressById(id));
    }
}
