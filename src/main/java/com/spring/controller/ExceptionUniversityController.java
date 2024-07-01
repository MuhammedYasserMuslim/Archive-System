package com.spring.controller;

import com.spring.model.entity.ExceptionUniversity;
import com.spring.services.ExceptionUniversityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/university")
public class ExceptionUniversityController {

    private ExceptionUniversityService exceptionUniversityService;


    @GetMapping("/university")
    public List<ExceptionUniversity> findAll() {
        return exceptionUniversityService.findAll();
    }

    @PostMapping("/university")
    public ExceptionUniversity insert(@RequestBody ExceptionUniversity exceptionUniversity) {
        return exceptionUniversityService.insert(exceptionUniversity);
    }

    @DeleteMapping("/university/{id}")
    public void delete(@PathVariable String id) {
        exceptionUniversityService.deleteById(id);
    }
}
