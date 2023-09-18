package com.employee.rest.controllers;

import com.employee.rest.controllers.DTO.EmployeeDTO;
import com.employee.rest.entities.Employee;
import com.employee.rest.services.IEmployeeService;
import com.employee.rest.utils.ValidFieldUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ValidFieldUtil validFieldUtil;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll(){
        Map<String, Object> response = new HashMap<>();

        // responder con un DTO
        List<EmployeeDTO> employeeList = employeeService.findAll()
                .stream()
                .map(employee -> EmployeeDTO.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .phone(employee.getPhone())
                        .address(employee.getAddress())
                        .build()
                ).toList();

        response.put("Data", employeeList);
        response.put("message", "Datos obtenidos");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> filterEmployee(@RequestParam String filter){
        Map<String, Object> response = new HashMap<>();

        List<EmployeeDTO> employeeDTOList = employeeService.filterEmployee(filter)
                .stream()
                .map(employee -> EmployeeDTO.builder()
                        .id(employee.getId())
                        .name(employee.getName())
                        .lastName(employee.getLastName())
                        .email(employee.getEmail())
                        .phone(employee.getPhone())
                        .address(employee.getAddress())
                        .build()
                ).toList();

        response.put("Data", employeeDTOList);
        response.put("message", "Datos obtenidos");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){

        Map<String, Object> response = new HashMap<>();

        Optional<Employee> employeeOptional = employeeService.findById(id);

        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get(); // obtener el objeto completo

            EmployeeDTO employeeDTO = EmployeeDTO.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .lastName(employee.getLastName())
                    .email(employee.getEmail())
                    .phone(employee.getPhone())
                    .address(employee.getAddress())
                    .build();

            response.put("Data", employeeDTO);
            response.put("message", "Datos obtenidos");

            return ResponseEntity.ok(response);
        }

        response.put("message", "Empleado con id "+ id +" no encontrado");
        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody EmployeeDTO employeeDTO){

        Map<String, Object> response = new HashMap<>();

        try {
            if(!validFieldUtil.isValidField(employeeDTO)){
                response.put("message", "Uno o varios campos estan vacios");
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            }

            employeeService.save(
                    Employee.builder()
                            .name(employeeDTO.getName())
                            .lastName(employeeDTO.getLastName())
                            .email(employeeDTO.getEmail())
                            .phone(employeeDTO.getPhone())
                            .address(employeeDTO.getAddress())
                            .build()
            );

            response.put("message", "Empleado creado");
            response.put("url", new URI("/api/v1/employee"));

            return ResponseEntity.ok(response);
        } catch (URISyntaxException e) {
            response.put("message", "Error interno");
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployed(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){

        Map<String, Object> response = new HashMap<>();

        Optional<Employee> employeeOptional = employeeService.findById(id);

        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();

            if(!validFieldUtil.isValidField(employeeDTO)){
                response.put("message", "Uno o varios campos estan vacios");
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            }

            employee.setName(employeeDTO.getName());
            employee.setLastName(employeeDTO.getLastName());
            employee.setEmail(employeeDTO.getEmail());
            employee.setPhone(employeeDTO.getPhone());
            employee.setAddress(employee.getAddress());

            employeeService.save(employee);

            response.put("message", "Empleado actualizado correctamente");

            return ResponseEntity.ok(response);
        }

        response.put("message", "El empleado con id " + id +" no encontrado");

        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();
        if(id == null){
            response.put("message", "el id no esta proporcionado");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Employee> employeeOptional = employeeService.findById(id);
        if(employeeOptional.isPresent()){
            employeeService.deleteById(id);
            response.put("message", "Empleado eliminado");
            return ResponseEntity.ok(response);
        }

        response.put("message", "Empleado con id " + id +" no encontrado");

        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
}
