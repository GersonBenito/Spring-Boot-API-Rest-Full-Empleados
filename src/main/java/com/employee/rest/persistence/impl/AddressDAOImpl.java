package com.employee.rest.persistence.impl;

import com.employee.rest.entities.Address;
import com.employee.rest.persistence.IAddressDAO;
import com.employee.rest.repositories.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AddressDAOImpl implements IAddressDAO {

    @Autowired
    private IAddressRepository addressRepository;
    @Override
    public List<Address> findAll() {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public Optional<Address> findBydId(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }
}
