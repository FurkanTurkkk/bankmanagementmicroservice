package com.bankmanagementmicroservice.customerservice.request;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RequestForCreateCustomer {
    private String firstName;
    private String lastName;
    private String tc;
    private Long addressId;
    private LocalDate birthDay;
    private String phoneNumber;

    public RequestForCreateCustomer() {
    }

    public RequestForCreateCustomer(String firstName,
                                    String lastName,
                                    String tc,
                                    LocalDate birthDay,
                                    Long addressId,
                                    String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tc = tc;
        this.birthDay = birthDay;
        this.addressId = addressId;
        this.phoneNumber = phoneNumber;
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

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public Long getAddressId() {
        return addressId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
