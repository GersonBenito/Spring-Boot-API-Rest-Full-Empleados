package com.employee.rest.repositories;

import com.employee.rest.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEmployeeRepository extends CrudRepository<Employee, Long> {

    // uso de JPQL
    @Query("SELECT e FROM Employee e WHERE e.email LIKE %?1% OR e.name LIKE %?1% OR e.lastName LIKE %?1% OR e.phone LIKE %?1%")
    public List<Employee> filterEmployee(String filter);
}
