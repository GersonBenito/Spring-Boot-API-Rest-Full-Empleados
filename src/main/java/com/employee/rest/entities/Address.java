package com.employee.rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "department_name")
    private String departmentName;

    @NotBlank
    @Column(name = "municipality_name")
    private String municipalityName;
    private String location;

    @OneToMany(targetEntity = Employee.class, fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.PERSIST)
    @JsonIgnore // decirle a Jackson que unicamente lo serialize cuando sea requerido de lo cotrario no
    private List<Employee> employees = new ArrayList<>();
}
