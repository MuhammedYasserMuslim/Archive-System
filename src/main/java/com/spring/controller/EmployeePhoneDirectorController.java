package com.spring.controller;


import com.spring.model.dto.phonedirector.EmployeePhoneDirectorDto;
import com.spring.model.dto.phonedirector.FacultyPhoneDirectoryDto;
import com.spring.services.EmployeePhoneDirectorServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/e-phone")
@Tag(name = "Employee Phone Director")
public class EmployeePhoneDirectorController {

    private final EmployeePhoneDirectorServices employeePhoneDirectorServices;


    @GetMapping("/e-phones")
    public ResponseEntity<List<EmployeePhoneDirectorDto>> findAll() {
        return ResponseEntity.ok(employeePhoneDirectorServices.findAll());
    }

    @GetMapping("/e-phone")
    public ResponseEntity<EmployeePhoneDirectorDto> findById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(employeePhoneDirectorServices.findById(id));
    }

    @PostMapping("/e-phone")
    public ResponseEntity<?> insert(@RequestBody EmployeePhoneDirectorDto teachingPhoneDirectorDto) {
        employeePhoneDirectorServices.insert(teachingPhoneDirectorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/e-phone")
    public ResponseEntity<?> update(@RequestBody EmployeePhoneDirectorDto teachingPhoneDirectorDto ,@RequestParam int id) {
        employeePhoneDirectorServices.update(teachingPhoneDirectorDto , id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/e-phone")
    public ResponseEntity<?> deleteById(@RequestParam int id) {
        employeePhoneDirectorServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
