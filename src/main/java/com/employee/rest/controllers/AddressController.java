package com.employee.rest.controllers;

import com.employee.rest.controllers.DTO.AddressDTO;
import com.employee.rest.entities.Address;
import com.employee.rest.services.IAddressService;
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
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private ValidFieldUtil validFieldUtil;

    @GetMapping
    public ResponseEntity<Map<String, Object>> findAll(){
       Map<String, Object> response = new HashMap<>();

        List<AddressDTO> addressDTO = addressService.findAll()
                .stream()
                .map(address -> AddressDTO.builder()
                        .id(address.getId())
                        .departmentName(address.getDepartmentName())
                        .municipalityName(address.getMunicipalityName())
                        .location(address.getLocation())
                        .employees(address.getEmployees())
                        .build()
                ).toList();

        response.put("Data", addressDTO);
        response.put("message", "Datos obtenidos");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();

        if(id == null){
            response.put("message", "id no esta presente");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Address> addressOptional = addressService.findBydId(id);

        if(addressOptional.isPresent()){
            Address address = addressOptional.get();

            AddressDTO addressDTO = AddressDTO.builder()
                    .id(address.getId())
                    .departmentName(address.getDepartmentName())
                    .municipalityName(address.getMunicipalityName())
                    .location(address.getLocation())
                    .employees(address.getEmployees())
                    .build();

            response.put("Data", addressDTO);
            response.put("message", "Datos obtenido");

            return ResponseEntity.ok(response);
        }

        response.put("message", "Direccion con id "+ id +" no encontrada");

        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressDTO addressDTO){
        Map<String, Object> response = new HashMap<>();

        try {
            if(!validFieldUtil.isValidField(addressDTO)){
                response.put("message", "Uno o varios campos estan vacios");
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            }

            addressService.save(
                    Address.builder()
                            .departmentName(addressDTO.getDepartmentName())
                            .municipalityName(addressDTO.getMunicipalityName())
                            .location(addressDTO.getLocation())
                            .employees(addressDTO.getEmployees())
                            .build()
            );

            response.put("message", "Direccion creado");
            response.put("url", new URI("/api/v1/address"));

            return ResponseEntity.ok(response);

        } catch (URISyntaxException e) {
            response.put("message", "Error interno");
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable Long id, @RequestBody AddressDTO addressDTO){
        Map<String, Object> response = new HashMap<>();

        if(id == null){
            response.put("message", "id no esta presente");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Address> addressOptional = addressService.findBydId(id);

        if(addressOptional.isPresent()){
            Address address = addressOptional.get();

            if(!validFieldUtil.isValidField(addressDTO)){
                response.put("message", "Uno o varios campos estanvacios");
                return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
            }

            address.setDepartmentName(addressDTO.getDepartmentName());
            address.setMunicipalityName(addressDTO.getMunicipalityName());
            address.setLocation(addressDTO.getLocation());
            address.setEmployees(addressDTO.getEmployees());

            addressService.save(address);
            response.put("message", "Direccion actualizado");
            return ResponseEntity.ok(response);
        }

        response.put("message", "Direccion con id "+ id +" no encontrado");

        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Map<String, Object> response = new HashMap<>();

        if(id == null){
            response.put("message", "id no esta presente");
            return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<Address> addressOptional = addressService.findBydId(id);

        if(addressOptional.isPresent()){
            response.put("message", "Direccion eliminado");
            addressService.deleteById(id);
            return ResponseEntity.ok(response);
        }

        response.put("message", "Direccion con id "+ id +" no encontrado");

        return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
    }
}
