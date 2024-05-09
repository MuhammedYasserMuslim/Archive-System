package com.spring.controller;

import com.spring.model.dto.phonedirector.FacultyPhoneDirectoryDto;
import com.spring.services.FacultyPhoneDirectoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/f-phone")
public class FacultyPhoneDirectoryController {

    private final FacultyPhoneDirectoryServices facultyPhoneDirectoryServices;


    @GetMapping("/f-phones")
    public ResponseEntity<List<FacultyPhoneDirectoryDto>> findAll() {
        return ResponseEntity.ok(facultyPhoneDirectoryServices.findAll());
    }

    @PostMapping("/f-phone")
    public ResponseEntity<?> insert(@RequestBody FacultyPhoneDirectoryDto dto) {
        facultyPhoneDirectoryServices.insert(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/f-phone")
    public ResponseEntity<?> deleteById(@RequestParam int id) {
        facultyPhoneDirectoryServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
