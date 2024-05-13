package com.spring.controller;


import com.spring.model.dto.phonedirector.EmployeePhoneDirectorDto;
import com.spring.services.EmployeePhoneDirectorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/e-phone")
public class EmployeePhoneDirectorController {

    private final EmployeePhoneDirectorServices employeePhoneDirectorServices;


    @GetMapping("/e-phones")
    public ResponseEntity<List<EmployeePhoneDirectorDto>> findAll(){
        return ResponseEntity.ok(employeePhoneDirectorServices.findAll());
    }

    @PostMapping("/e-phone")
    public ResponseEntity<?>insert(@RequestBody EmployeePhoneDirectorDto teachingPhoneDirectorDto){
        employeePhoneDirectorServices.insert(teachingPhoneDirectorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/e-phone")
    public ResponseEntity<?> deleteById(@RequestParam int id){
        employeePhoneDirectorServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
