package com.spring.controller;

import com.spring.model.dto.special.SpecialDto;
import com.spring.model.entity.Special;
import com.spring.model.entity.Subject;
import com.spring.services.SpecialServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/special")
@RequiredArgsConstructor
public class SpecialController {


    private final SpecialServices specialServices;

    @GetMapping("/specials")
    public ResponseEntity<List<SpecialDto>> findAll() {
        return new ResponseEntity<>(specialServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/special")
    public ResponseEntity<List<SpecialDto>> findBySubject(String summary) {
        return new ResponseEntity<>(specialServices.findBySubject(summary), HttpStatus.OK);
    }

    @PostMapping("/special")
    public ResponseEntity<?> insert(@RequestBody Special special) {
        specialServices.insert(special);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

