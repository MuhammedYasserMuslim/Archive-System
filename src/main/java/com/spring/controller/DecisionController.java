package com.spring.controller;


import com.spring.model.entity.Decision;
import com.spring.services.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/decision")
@CrossOrigin("http://localhost:4200")
public class DecisionController {

    private final DecisionService decisionService;

    @GetMapping("/decisions")
    public ResponseEntity<List<Decision>> findAll() {
        return new ResponseEntity<>(decisionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/decision-summary")
    public ResponseEntity<List<Decision>> findBySummary(String summary) {
        return new ResponseEntity<>(decisionService.findBySummary(summary), HttpStatus.OK);
    }
}
