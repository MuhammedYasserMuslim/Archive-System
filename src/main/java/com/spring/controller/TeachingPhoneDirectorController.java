package com.spring.controller;


import com.spring.model.dto.phonedirector.TeachingPhoneDirectorDto;
import com.spring.services.TeachingPhoneDirectorServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/t-phone")
@Tag(name ="Teaching Phone Director" )
public class TeachingPhoneDirectorController {

    private final TeachingPhoneDirectorServices teachingPhoneDirectorServices;


    @GetMapping("/t-phones")
    public ResponseEntity<List<TeachingPhoneDirectorDto>> findAll(){
        return ResponseEntity.ok(teachingPhoneDirectorServices.findAll());
    }

    @GetMapping("/t-phone")
    public ResponseEntity<TeachingPhoneDirectorDto> findById(@RequestParam("id") Integer id){
        return ResponseEntity.ok(teachingPhoneDirectorServices.findById(id));
    }

    @PostMapping("/t-phone")
    public ResponseEntity<?>insert(@RequestBody TeachingPhoneDirectorDto teachingPhoneDirectorDto){
        teachingPhoneDirectorServices.insert(teachingPhoneDirectorDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/t-phone")
    public ResponseEntity<?>update(@RequestBody TeachingPhoneDirectorDto teachingPhoneDirectorDto,@RequestParam int id){
        teachingPhoneDirectorServices.update(teachingPhoneDirectorDto,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/t-phone")
    public ResponseEntity<?> deleteById(@RequestParam int id){
        teachingPhoneDirectorServices.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
