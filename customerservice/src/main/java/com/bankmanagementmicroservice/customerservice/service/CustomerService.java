package com.bankmanagementmicroservice.customerservice.service;


import com.bankmanagementmicroservice.adressservice.model.Address;
import com.bankmanagementmicroservice.customerservice.feignclient.AddressServiceFeignClient;
import com.bankmanagementmicroservice.customerservice.dtoconverter.CustomerDtoConverter;
import com.bankmanagementmicroservice.customerservice.exception.CustomerExistException;
import com.bankmanagementmicroservice.customerservice.exception.CustomerNotFoundException;
import com.bankmanagementmicroservice.customerservice.model.Customer;
import com.bankmanagementmicroservice.customerservice.repository.CustomerRepository;
import com.bankmanagementmicroservice.customerservice.request.RequestForCreateCustomer;
import jakarta.transaction.Transactional;
import org.example.AddressDto;
import org.example.CustomerDto;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressServiceFeignClient addressServiceFeignClient;

    public CustomerService(CustomerRepository customerRepository, AddressServiceFeignClient addressServiceFeignClient) {
        this.customerRepository = customerRepository;
        this.addressServiceFeignClient = addressServiceFeignClient;
    }

    private Customer createNewCustomer(RequestForCreateCustomer request){
        if(customerRepository.findByTcAndAddressId(request.getTc(),request.getAddressId()).isPresent()){
            throw new CustomerExistException("Customer already exist");
        }else {
            Customer customer=new Customer(
                    request.getFirstName(),
                    request.getLastName(),
                    request.getTc(),
                    request.getAddressId(),
                    request.getBirthDay(),
                    request.getPhoneNumber()
            );
            return customerRepository.save(customer);
        }
    }


    private Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(()->new CustomerNotFoundException("Customer could not found by id : "+id));
    }

    public CustomerDto getCustomerById(Long id){
        Customer customer=findCustomerById(id);
        AddressDto addressDto= addressServiceFeignClient.findAddressByAddressId(customer.getAddressId()).getBody();
        return CustomerDtoConverter.convert(customer,addressDto);
    }

    @Transactional
    public CustomerDto addCustomer(RequestForCreateCustomer request){
        Customer registeredCustomer=createNewCustomer(request);
        AddressDto addressDto= addressServiceFeignClient.findAddressByAddressId(request.getAddressId()).getBody();
        return CustomerDtoConverter.convert(registeredCustomer,addressDto);
    }

    @Transactional
    public CustomerDto updateAddressOfCustomerById(Long customerId,Long addressId){
        Customer customer=findCustomerById(customerId);
        customer.updateAddressId(addressId);
        AddressDto address=addressServiceFeignClient.findAddressByAddressId(addressId).getBody();
        return CustomerDtoConverter.convert(customer,address);
    }

    public void deleteCustomerById(Long id){
        findCustomerById(id);
        customerRepository.deleteById(id);
    }

    public void deleteAllCustomer(){
        customerRepository.deleteAll();
    }
}
