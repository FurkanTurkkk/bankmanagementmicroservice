package com.bankmanagementmicroservice.adressservice.dtoconverter;

import com.bankmanagementmicroservice.adressservice.dto.AddressDto;
import com.bankmanagementmicroservice.adressservice.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoConverter {
    public AddressDto convert(Address address){
        return new AddressDto(
                address.getCity(),
                address.getStreet(),
                address.getApartmentNo(),
                address.getDoorNumber()
        );
    }
}
