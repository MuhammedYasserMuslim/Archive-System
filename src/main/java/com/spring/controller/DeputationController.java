package com.spring.controller;

import com.spring.model.entity.Deputation;
import com.spring.services.DeputationServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deputation")
@RequiredArgsConstructor
public class DeputationController {

    private final DeputationServices deputationServices;

    @GetMapping("/deputations")
    public List<Deputation> getDeputations() {
        return deputationServices.findAll();
    }
}
