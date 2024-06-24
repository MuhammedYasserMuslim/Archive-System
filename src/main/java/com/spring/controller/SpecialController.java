package com.spring.controller;

import com.spring.model.dto.special.SpecialDto;
import com.spring.model.dto.special.SpecialDtoPost;
import com.spring.services.SpecialServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/special")
@RequiredArgsConstructor
@Tag(name = "Special")
public class SpecialController {


    private final SpecialServices specialServices;


    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        return new ResponseEntity<>(specialServices.count(), HttpStatus.OK);
    }

    @GetMapping("/count-current")
    public ResponseEntity<Integer> countCurrent() {
        return new ResponseEntity<>(specialServices.countCurrent(), HttpStatus.OK);
    }

    @GetMapping("/all-specials")
    public ResponseEntity<List<SpecialDto>> findAll() {
        return new ResponseEntity<>(specialServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/specials-pagination")
    public ResponseEntity<SpecialDto> findAllPagination(@RequestParam int page) {
        return new ResponseEntity<>(specialServices.findAllPagination(page - 1), HttpStatus.OK);
    }


    @GetMapping("/specials")
    public ResponseEntity<List<SpecialDto>> findByYear() {
        return new ResponseEntity<>(specialServices.findByYear(), HttpStatus.OK);
    }

    @GetMapping("/special-summary")
    public ResponseEntity<List<SpecialDto>> findBySubject(@RequestParam String summary) {
        return new ResponseEntity<>(specialServices.findBySubject(summary), HttpStatus.OK);
    }

    @GetMapping("/special-archive")
    public ResponseEntity<List<SpecialDto>> findByArchiveFile(@RequestParam short id) {
        return new ResponseEntity<>(specialServices.findByArchiveFile(id), HttpStatus.OK);
    }

    @GetMapping("/special-id")
    public ResponseEntity<SpecialDto> findById(@RequestParam int id) {
        return new ResponseEntity<>(specialServices.findById(id), HttpStatus.OK);
    }

    @GetMapping("/count-special-by-year")
    public ResponseEntity<Integer> findByYearDate(@RequestParam String year) throws StringIndexOutOfBoundsException {
        return new ResponseEntity<>(specialServices.findByYearDate(year.substring(5, 9)), HttpStatus.OK);
    }

    @PostMapping("/special")
    public ResponseEntity<?> insert(@RequestBody SpecialDtoPost special) {
        specialServices.insert(special);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/specials")
    public ResponseEntity<?> insertALl(@RequestBody List<SpecialDtoPost> specials) {
        for (SpecialDtoPost special : specials)
            specialServices.insert(special);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/special")
    public ResponseEntity<?> update(@RequestBody SpecialDtoPost special, @RequestParam int id) {
        specialServices.update(special, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

