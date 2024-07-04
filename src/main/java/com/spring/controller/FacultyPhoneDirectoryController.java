package com.spring.controller;

import com.spring.model.dto.phonedirector.FacultyPhoneDirectoryDto;
import com.spring.model.dto.phonedirector.TeachingPhoneDirectorDto;
import com.spring.services.FacultyPhoneDirectoryServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/f-phone")
@Tag(name = " Faculty Phone Director")
public class FacultyPhoneDirectoryController {

    private final FacultyPhoneDirectoryServices facultyPhoneDirectoryServices;


    @GetMapping("/f-phones")
    public ResponseEntity<List<FacultyPhoneDirectoryDto>> findAll() {
        return ResponseEntity.ok(facultyPhoneDirectoryServices.findAll());
    }

    @GetMapping("/f-phone")
    public ResponseEntity<FacultyPhoneDirectoryDto> findById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(facultyPhoneDirectoryServices.findById(id));
    }

    @PostMapping("/f-phone")
    public ResponseEntity<?> insert(@RequestBody FacultyPhoneDirectoryDto dto) {
        facultyPhoneDirectoryServices.insert(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/f-phone")
    public ResponseEntity<?> update(@RequestBody FacultyPhoneDirectoryDto dto,@RequestParam int id) {
        facultyPhoneDirectoryServices.update(dto,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/f-phone")
    public ResponseEntity<?> deleteById(@RequestParam int id) {
        facultyPhoneDirectoryServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
