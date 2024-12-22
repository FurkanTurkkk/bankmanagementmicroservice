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

    private String country;
    private String city;
    private String town;
    private String street;
    private Long apartmentNo;
    private Long doorNumber;

    public Address() {
    }

    public Address(String country,
                   String city,
                   String town,
                   String street,
                   Long apartmentNo, Long doorNumber) {
        this.country=country;
        this.city = city;
        this.town=town;
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

    public String getCountry() {
        return country;
    }

    public String getTown() {
        return town;
    }
}
