package com.spring.controller;

import com.spring.model.dto.deputation.DeputationDays;
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

    @GetMapping("/count-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countDeputations() {
        return deputationServices.countByYear();
    }

    @GetMapping("/all-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findAll() {
        return deputationServices.findAll();
    }


    @GetMapping("/accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findAccepted() {
        return deputationServices.findAccepted();
    }

    @GetMapping("/count-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countAccepted() {
        return deputationServices.countAccepted();
    }

    @GetMapping("/not-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findNotAccepted() {
        return deputationServices.findNotAccepted();
    }

    @GetMapping("/count-not-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countNotAccepted() {
        return deputationServices.countNotAccepted();
    }

    @GetMapping("/current-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findCurrentDeputation() {
        return deputationServices.findCurrentDeputation();
    }

    @GetMapping("/count-current-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countCurrentDeputation() {
        return deputationServices.countCurrentDeputation();
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
    public List<DeputationDays>findDeputationDays(){
        return deputationServices.findDeputationDays();
    }

    @PostMapping("/deputation")
    @ResponseStatus(HttpStatus.CREATED)
    public DeputationDto createDeputation(@RequestBody DeputationDto deputationDto) {
        return deputationServices.insert(deputationDto);
    }

    @PutMapping("/deputation")
    @ResponseStatus(HttpStatus.OK)
    public void updateDeputation(@RequestBody DeputationDto deputationDto, @RequestParam int id) {
        deputationServices.update(deputationDto, id);
    }

    @DeleteMapping("/deputation")
    @ResponseStatus(HttpStatus.OK)
    public void deleteById(@RequestParam Integer id) {
        deputationServices.deleteById(id);
    }
}
