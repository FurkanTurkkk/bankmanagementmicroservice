package com.bankmanagementmicroservice.customerservice.dtoconverter;

import com.bankmanagementmicroservice.customerservice.model.Customer;
import org.example.AddressDto;
import org.example.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public static CustomerDto convert(Customer customer, AddressDto address){
        return new CustomerDto(
                customer.getFirstName(),
                customer.getLastName(),
                customer.getTc(),
                address,
                customer.getBirthDay(),
                customer.getPhoneNumber()
        );
    }
}
