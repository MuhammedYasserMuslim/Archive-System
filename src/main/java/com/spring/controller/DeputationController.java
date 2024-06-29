package com.spring.controller;

import com.spring.model.dto.deputation.DeputationDto;
import com.spring.model.entity.Deputation;
import com.spring.services.DeputationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deputation")
@RequiredArgsConstructor
public class DeputationController {

    private final DeputationServices deputationServices;

    @GetMapping("/deputations")
    public List<DeputationDto> getDeputations() {
        return deputationServices.findAll();
    }

    @PostMapping("/deputation")
    public DeputationDto createDeputation(@RequestBody DeputationDto deputationDto) {
        return deputationServices.save(deputationDto);
    }
}
