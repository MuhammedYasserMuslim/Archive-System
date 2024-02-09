package com.spring.controller;


import com.spring.model.entity.Subject;
import com.spring.services.SubjectServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectServices subjectServices;

    @GetMapping("/subjects")
    public ResponseEntity<List<Subject>> findAll() {
        return new ResponseEntity<>(subjectServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/subject")
    public ResponseEntity<List<Subject>> findBySummary(@RequestParam String summary) {
        return new ResponseEntity<>(subjectServices.findBySummaryContaining(summary), HttpStatus.OK);
    }


    @PostMapping("/subject")
    public ResponseEntity<?> insert(@RequestBody Subject subject) {
        subjectServices.insert(subject);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
