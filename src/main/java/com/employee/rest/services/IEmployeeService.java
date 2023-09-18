package com.employee.rest.services;

import com.employee.rest.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    public List<Employee> findAll();

    public List<Employee> filterEmployee(String filter);

    public Optional<Employee> findById(Long id);

    public void save(Employee employee);

    public void deleteById(Long id);
}
