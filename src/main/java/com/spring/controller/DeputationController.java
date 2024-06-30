package com.spring.controller;

import com.spring.model.dto.deputation.DeputationDto;
import com.spring.services.DeputationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deputation")
@RequiredArgsConstructor
public class DeputationController {

    private final DeputationServices deputationServices;

    @GetMapping("/deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findByYear() {
        return deputationServices.findByYear();
    }

    @GetMapping("/all-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findAll() {
        return deputationServices.findAll();
    }

    @PostMapping("/deputation")
    @ResponseStatus(HttpStatus.CREATED)
    public DeputationDto createDeputation(@RequestBody DeputationDto deputationDto) {
        return deputationServices.insert(deputationDto);
    }

    @PutMapping("/deputation")
    @ResponseStatus(HttpStatus.OK)
    public void updateDeputation(@RequestBody DeputationDto deputationDto ,@RequestParam int id) {
         deputationServices.update(deputationDto , id);
    }
}
