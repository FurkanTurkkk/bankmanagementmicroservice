package com.bankmanagementmicroservice.customerservice.repository;

import com.bankmanagementmicroservice.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Optional<Customer> findByTcAndAddressId(String tc,Long addressId);
    Optional<Customer> findByTcAndPhoneNumber(String tc,String phoneNumber);
}
