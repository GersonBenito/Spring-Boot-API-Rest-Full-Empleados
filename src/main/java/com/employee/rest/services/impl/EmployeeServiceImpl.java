package com.employee.rest.services.impl;

import com.employee.rest.entities.Employee;
import com.employee.rest.persistence.IEmployeeDAO;
import com.employee.rest.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeDAO employeeDAO;

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public List<Employee> filterEmployee(String filter) {
        return employeeDAO.filterEmployee(filter);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employeeDAO.findById(id);
    }

    @Override
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeDAO.deleteById(id);
    }
}
