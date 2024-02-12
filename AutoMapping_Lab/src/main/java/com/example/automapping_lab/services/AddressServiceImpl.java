package com.example.automapping_lab.services;

import com.example.automapping_lab.entities.Address;
import com.example.automapping_lab.entities.dtos.AddressDTO;
import com.example.automapping_lab.repositories.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


}
