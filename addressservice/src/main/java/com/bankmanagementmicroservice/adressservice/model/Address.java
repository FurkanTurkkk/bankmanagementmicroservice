package com.bankmanagementmicroservice.adressservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String street;
    private Long apartmentNo;
    private Long doorNumber;

    public Address() {
    }

    public Address(String city, String street, Long apartmentNo, Long doorNumber) {
        this.city = city;
        this.street = street;
        this.apartmentNo = apartmentNo;
        this.doorNumber = doorNumber;
    }

    public Long getId() {
        return id;
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
