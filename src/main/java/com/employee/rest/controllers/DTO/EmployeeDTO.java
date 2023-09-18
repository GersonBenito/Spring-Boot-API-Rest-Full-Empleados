package com.employee.rest.controllers.DTO;

import com.employee.rest.entities.Address;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeeDTO {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
}
