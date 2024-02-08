package com.spring.controller;

import com.spring.model.entity.Special;
import com.spring.services.SpecialServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/special")
@RequiredArgsConstructor
public class SpecialController {


    private final SpecialServices specialServices;

    @GetMapping("/specials")
    public ResponseEntity<List<Special>> findAll() {
        return new ResponseEntity<>(specialServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/special")
    public ResponseEntity<List<Special>> findBySubject(String summary) {
        return new ResponseEntity<>(specialServices.findBySubject(summary), HttpStatus.OK);
    }

}

