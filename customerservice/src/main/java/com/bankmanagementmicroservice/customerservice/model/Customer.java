package com.bankmanagementmicroservice.customerservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String tc;
    private Long addressId;
    private LocalDate birthDay;
    private String phoneNumber;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String tc, Long addressId, LocalDate birthDay, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.tc = tc;
        this.addressId = addressId;
        this.birthDay = birthDay;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
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

    public Long getAddressId() {
        return addressId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(tc, customer.tc)
                && Objects.equals(addressId, customer.addressId)
                && Objects.equals(phoneNumber, customer.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tc, addressId, phoneNumber);
    }
}
