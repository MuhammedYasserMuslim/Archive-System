package com.spring.controller;

import com.spring.model.dto.deputation.DeputationDays;
import com.spring.model.dto.deputation.DeputationDto;
import com.spring.model.dto.deputation.DeputationPost;
import com.spring.model.entity.Deputation;
import com.spring.model.entity.ExceptionUniversity;
import com.spring.security.jwt.JwtServices;
import com.spring.services.DeputationServices;
import com.spring.services.ExceptionUniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/deputation")
@RequiredArgsConstructor
public class DeputationController {

    private final DeputationServices deputationServices;
    private final JwtServices jwtServices;
    private final ExceptionUniversityService exceptionUniversityService;

    @GetMapping("/deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findByYear(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findByYear();
        return deputationServices.findByYear().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();
    }

    @GetMapping("/count-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countDeputations(@RequestHeader("Authorization") String token) {
        return findByYear(token).size();
    }

    @GetMapping("/all-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findAll() {
        return deputationServices.findAll();
    }

    @GetMapping("/deputation")
    @ResponseStatus(HttpStatus.OK)
    public DeputationPost findById(@RequestParam int id) {
        return deputationServices.findByIdPost(id);
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

    @GetMapping("/non-exception-deputation")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findNotExceptionDeputation() {
        return deputationServices.findNotExceptionDeputation();
    }

    @GetMapping("/count-non-exception-deputation")
    @ResponseStatus(HttpStatus.OK)
    public Integer countNotExceptionDeputation() {
        return deputationServices.findNotExceptionDeputation().size();
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


    private List<String> universities() {
        List<String> universities = new ArrayList<>();
        for (ExceptionUniversity university : exceptionUniversityService.findAll()) {
            universities.add(university.getUniversity());
        }
        return universities;
    }
}