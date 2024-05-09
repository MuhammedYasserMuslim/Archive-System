package com.spring.controller;


import com.spring.model.dto.phonedirector.TeachingPhoneDirectorDto;
import com.spring.services.TeachingPhoneDirectorServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/t-phone")
public class TeachingPhoneDirectorController {

    private final TeachingPhoneDirectorServices teachingPhoneDirectorServices;


    @GetMapping("/t-phones")
    public ResponseEntity<List<TeachingPhoneDirectorDto>> findAll(){
        return ResponseEntity.ok(teachingPhoneDirectorServices.findAll());
    }

    @PostMapping("/t-phone")
    public ResponseEntity<?>insert(@RequestBody TeachingPhoneDirectorDto teachingPhoneDirectorDto){
        teachingPhoneDirectorServices.insert(teachingPhoneDirectorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/t-phone")
    public ResponseEntity<?> deleteById(@RequestParam int id){
        teachingPhoneDirectorServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
