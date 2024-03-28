package com.spring.controller;

import com.spring.services.BaseDataServices;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    private final BaseDataServices baseDataServices;

    @GetMapping("/years")
    public ResponseEntity<List<String>> findYears() {
        return new ResponseEntity<>(baseDataServices.findYears(), HttpStatus.OK);
    }


}
