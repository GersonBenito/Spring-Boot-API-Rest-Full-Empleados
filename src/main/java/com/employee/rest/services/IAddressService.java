package com.employee.rest.services;

import com.employee.rest.entities.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressService {
    public List<Address> findAll();

    public Optional<Address> findBydId(Long id);

    public void save(Address address);

    public void deleteById(Long id);
}
