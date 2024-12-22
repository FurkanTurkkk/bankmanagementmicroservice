package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String tc;
    private AddressDto addressDto;
    private LocalDate birthDay;
    private String phoneNumber;

    public CustomerDto() {
    }

    public CustomerDto(String firstName,
                       String lastName,
                       String tc,
                       AddressDto addressDto,
                       LocalDate birthDay,
                       String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tc = tc;
        this.addressDto = addressDto;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTc() {
        return tc;
    }

    public AddressDto getAddressId() {
        return addressDto;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}
