package com.spring.controller;

import com.spring.services.GeneralServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/general")
public class GeneralController {

    private final GeneralServices generalServices;

    @GetMapping("/years")
    public ResponseEntity<List<String>> findYears() {
        return new ResponseEntity<>(generalServices.findYears(), HttpStatus.OK);

    }

}
