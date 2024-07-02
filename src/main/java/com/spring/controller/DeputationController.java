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
    public List<DeputationDto> findAll(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findAll();
        return deputationServices.findAll().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();
    }

    @GetMapping("/deputation")
    @ResponseStatus(HttpStatus.OK)
    public DeputationPost findById(@RequestParam int id) {
        return deputationServices.findByIdPost(id);
    }

    @GetMapping("/accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findAccepted(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findAccepted();
        return deputationServices.findAccepted().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();

    }

    @GetMapping("/count-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countAccepted(@RequestHeader("Authorization") String token) {
        return findAccepted(token).size();
    }

    @GetMapping("/not-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findNotAccepted(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findNotAccepted();
        return deputationServices.findNotAccepted().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();

    }

    @GetMapping("/count-not-accepted-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countNotAccepted(@RequestHeader("Authorization") String token) {
        return findNotAccepted(token).size();
    }

    @GetMapping("/current-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findCurrentDeputation(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findCurrentDeputation();
        return deputationServices.findCurrentDeputation().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();

    }

    @GetMapping("/count-current-deputations")
    @ResponseStatus(HttpStatus.OK)
    public Integer countCurrentDeputation(@RequestHeader("Authorization") String token) {
        return findCurrentDeputation(token).size();
    }

    @GetMapping("/today-deputations")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findTodayDeputation(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findTodayDeputation();
        return deputationServices.findTodayDeputation().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();

    }

    @GetMapping("/today-in")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findTodayIn(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findTodayIn();
        return deputationServices.findTodayIn().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();

    }

    @GetMapping("/deputation-days")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDays> findDeputationDays() {
            return deputationServices.findDeputationDays();
    }

    @GetMapping("/exception-deputation")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findExceptionDeputation(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findExceptionDeputation();
        return deputationServices.findExceptionDeputation().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();

    }


    @GetMapping("/count-exception-deputation")
    @ResponseStatus(HttpStatus.OK)
    public Integer countExceptionDeputation(@RequestHeader("Authorization") String token) {
        return findExceptionDeputation(token).size();
    }

    @GetMapping("/non-exception-deputation")
    @ResponseStatus(HttpStatus.OK)
    public List<DeputationDto> findNotExceptionDeputation(@RequestHeader("Authorization") String token) {
        if (jwtServices.extractRole(token.substring(7)).equals("admin"))
            return deputationServices.findNotExceptionDeputation();
        return deputationServices.findNotExceptionDeputation().stream().filter(dto -> !universities().contains(dto.getDeputationUniversity())).toList();

    }

    @GetMapping("/count-non-exception-deputation")
    @ResponseStatus(HttpStatus.OK)
    public Integer countNotExceptionDeputation(@RequestHeader("Authorization") String token) {
        return findNotExceptionDeputation(token).size();
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