package com.spring.controller;

import com.spring.model.dto.deputation.DeputationDays;
import com.spring.model.dto.deputation.DeputationDto;
import com.spring.model.dto.deputation.DeputationPost;
import com.spring.model.entity.Deputation;
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

    @GetMapping("/count-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countDeputations() {
        return deputationServices.findByYear().size();
    }

    @GetMapping("/all-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findAll() {
        return deputationServices.findAll();
    }

    @GetMapping("/deputation")
    @ResponseStatus(HttpStatus.OK)
    public Deputation findById(@RequestParam int id) {
        return deputationServices.findById(id);
    }

    @GetMapping("/accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findAccepted() {
        return deputationServices.findAccepted();
    }

    @GetMapping("/count-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countAccepted() {
        return deputationServices.findAccepted().size();
    }

    @GetMapping("/not-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findNotAccepted() {
        return deputationServices.findNotAccepted();
    }

    @GetMapping("/count-not-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countNotAccepted() {
        return deputationServices.findNotAccepted().size();
    }

    @GetMapping("/current-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findCurrentDeputation() {
        return deputationServices.findCurrentDeputation();
    }

    @GetMapping("/count-current-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countCurrentDeputation() {
        return deputationServices.findCurrentDeputation().size();
    }

    @GetMapping("/today-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findTodayDeputation() {
        return deputationServices.findTodayDeputation();
    }

    @GetMapping("/today-in")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findTodayIn() {
        return deputationServices.findTodayIn();
    }

    @GetMapping("/deputation-days")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDays> findDeputationDays() {
        return deputationServices.findDeputationDays();
    }

    @GetMapping("/exception-deputation")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findExceptionDeputation() {
        return deputationServices.findExceptionDeputation();
    }

    @GetMapping("/count-exception-deputation")
    @ResponseStatus(HttpStatus.OK)
    public Integer countExceptionDeputation() {
        return deputationServices.findExceptionDeputation().size();
    }

    @PostMapping("/deputation")
    @ResponseStatus(HttpStatus.CREATED)
    public void createDeputation(@RequestBody DeputationPost dto) {
        deputationServices.insert(dto);
    }

    @PutMapping("/deputation")
    @ResponseStatus(HttpStatus.OK)
    public void updateDeputation(@RequestBody DeputationPost deputationPost, @RequestParam int id) {
        deputationServices.update(deputationPost, id);
    }

    @DeleteMapping("/deputation")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@RequestParam Integer id) {
        deputationServices.deleteById(id);
    }

}
