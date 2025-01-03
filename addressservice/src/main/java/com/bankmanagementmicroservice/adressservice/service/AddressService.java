package com.bankmanagementmicroservice.adressservice.service;

import com.bankmanagementmicroservice.adressservice.dtoconverter.AddressDtoConverter;
import com.bankmanagementmicroservice.adressservice.exception.AddressAlreadyExistException;
import com.bankmanagementmicroservice.adressservice.exception.AddressNotFoundException;
import com.bankmanagementmicroservice.adressservice.model.Address;
import com.bankmanagementmicroservice.adressservice.repository.AddressRepository;
import com.bankmanagementmicroservice.adressservice.request.RequestForCreateAddress;
import org.example.AddressDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressDtoConverter converter;

    public AddressService(AddressRepository addressRepository, AddressDtoConverter converter) {
        this.addressRepository = addressRepository;
        this.converter = converter;
    }

    private Address createNewAddress(RequestForCreateAddress request){

        return addressRepository.save(new Address(
                request.getCountry(),
                request.getCity(),
                request.getTown(),
                request.getStreet(),
                request.getApartmentNo(),
                request.getDoorNumber()));
    }

    private Address findAddressById(Long id){
        return addressRepository.findById(id)
                .orElseThrow(()->new AddressNotFoundException("Address could not found by id : "+id));
    }

    private List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public AddressDto getAddressById(Long id){
        return converter.convert(findAddressById(id));
    }

    public AddressDto addAddress(RequestForCreateAddress request){
        Address address=new Address(
                request.getCountry(),
                request.getCity(),
                request.getTown(),
                request.getStreet(),
                request.getApartmentNo(),
                request.getDoorNumber()
        );
        if(getAllAddress().contains(address)){
            throw new AddressAlreadyExistException("Address already exist");
        }
        return converter.convert(createNewAddress(request));
    }

    public void deleteAddressById(Long id){
        if(findAddressById(id)==null){
            throw new AddressNotFoundException("Address could not found by id : "+id);
        }
        addressRepository.deleteById(id);
    }
}
