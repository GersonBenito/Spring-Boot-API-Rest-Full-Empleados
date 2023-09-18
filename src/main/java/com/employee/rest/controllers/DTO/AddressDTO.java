package com.employee.rest.controllers.DTO;

import com.employee.rest.entities.Employee;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddressDTO {
    private Long id;
    private String departmentName;
    private String municipalityName;
    private String location;
    private List<Employee> employees;
}
