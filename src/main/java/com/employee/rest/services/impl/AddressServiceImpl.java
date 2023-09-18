package com.employee.rest.services.impl;

import com.employee.rest.entities.Address;
import com.employee.rest.persistence.IAddressDAO;
import com.employee.rest.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private IAddressDAO addressDAO;
    @Override
    public List<Address> findAll() {
        return addressDAO.findAll();
    }

    @Override
    public Optional<Address> findBydId(Long id) {
        return addressDAO.findBydId(id);
    }

    @Override
    public void save(Address address) {
        addressDAO.save(address);
    }

    @Override
    public void deleteById(Long id) {
        addressDAO.deleteById(id);
    }
}
