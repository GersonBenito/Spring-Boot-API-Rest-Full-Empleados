package com.employee.rest.persistence;

import com.employee.rest.entities.Address;

import java.util.List;
import java.util.Optional;

public interface IAddressDAO {

    public List<Address> findAll();

    public Optional<Address> findBydId(Long id);

    public void save(Address address);

    public void deleteById(Long id);
}
