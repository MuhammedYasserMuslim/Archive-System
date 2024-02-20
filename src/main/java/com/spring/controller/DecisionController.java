package com.spring.controller;


import com.spring.model.dto.special.DecisionDto;
import com.spring.model.entity.Decision;
import com.spring.services.DecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/decision")
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

    @PostMapping("/decision")
    public void insert(@RequestBody DecisionDto dto) {
        decisionService.insert(dto);
    }
}
