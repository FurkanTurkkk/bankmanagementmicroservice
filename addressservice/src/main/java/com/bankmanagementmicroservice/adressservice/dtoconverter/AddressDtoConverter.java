package com.bankmanagementmicroservice.adressservice.dtoconverter;

import com.bankmanagementmicroservice.adressservice.model.Address;
import org.example.AddressDto;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter {
    public AddressDto convert(Address address){
        return new AddressDto(
                address.getCountry(),
                address.getCity(),
                address.getTown(),
                address.getStreet(),
                address.getApartmentNo(),
                address.getDoorNumber()
        );
    }
}
