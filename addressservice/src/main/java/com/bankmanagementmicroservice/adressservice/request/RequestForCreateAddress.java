package com.bankmanagementmicroservice.adressservice.request;

import org.springframework.stereotype.Component;

@Component
public class RequestForCreateAddress {
    private String city;
    private String street;
    private Long apartmentNo;
    private Long doorNumber;

    public RequestForCreateAddress() {
    }

    public RequestForCreateAddress(String city, String street, Long apartmentNo, Long doorNumber) {
        this.city = city;
        this.street = street;
        this.apartmentNo = apartmentNo;
        this.doorNumber = doorNumber;
    }


    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public Long getApartmentNo() {
        return apartmentNo;
    }

    public Long getDoorNumber() {
        return doorNumber;
    }
}
