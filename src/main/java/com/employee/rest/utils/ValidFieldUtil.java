package com.employee.rest.utils;

import com.employee.rest.controllers.DTO.AddressDTO;
import com.employee.rest.controllers.DTO.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class ValidFieldUtil {
    public boolean isValidField(EmployeeDTO employeeDTO){
        return !(employeeDTO.getName().isBlank() || employeeDTO.getLastName().isBlank() || employeeDTO.getEmail().isBlank() || employeeDTO.getPhone().isBlank() || employeeDTO.getAddress() == null);
    }

    public boolean isValidField(AddressDTO addressDTO){
        return !(addressDTO.getDepartmentName().isBlank() || addressDTO.getMunicipalityName().isBlank() || addressDTO.getLocation().isBlank());
    }
}
