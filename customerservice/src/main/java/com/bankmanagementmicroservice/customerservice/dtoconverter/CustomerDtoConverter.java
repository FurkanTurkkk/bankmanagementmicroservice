package com.bankmanagementmicroservice.customerservice.dtoconverter;

import com.bankmanagementmicroservice.customerservice.client.AddressServiceClient;
import com.bankmanagementmicroservice.customerservice.model.Customer;
import org.example.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {
    private final AddressServiceClient addressServiceClient;

    public CustomerDtoConverter(AddressServiceClient addressServiceClient) {
        this.addressServiceClient = addressServiceClient;
    }

    public CustomerDto convert(Customer customer){
        AddressDto addressDto=addressServiceClient.findAddressByAddressId(customer.getAddressId()).getBody();
        return new CustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getTc(),
                addressDto,
                customer.getBirthDay(),
                customer.getPhoneNumber()
        );
    }
}
