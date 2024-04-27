package com.spring.controller;

import com.spring.model.dto.incomesigns.IncomingSignsDto;
import com.spring.model.dto.incomesigns.IncomingSignsDtoPost;
import com.spring.services.IncomingSignsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign")
public class IncomingSignsController {

    private final IncomingSignsServices incomingSignsServices;


    @GetMapping("/count")
    public ResponseEntity<Long> count(){
        return new ResponseEntity<>(incomingSignsServices.count(),HttpStatus.OK);
    }
    @GetMapping("/signs")
    public ResponseEntity<List<IncomingSignsDto>> findAll() {
        return new ResponseEntity<>(incomingSignsServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/sign")
    public ResponseEntity<IncomingSignsDto> findById(@RequestParam int id) {
        return new ResponseEntity<>(incomingSignsServices.findById(id), HttpStatus.OK);
    }

    @GetMapping("/sign-pagination")
    public ResponseEntity<IncomingSignsDto> findAllPagination(@RequestParam int page ) {
        return new ResponseEntity<>(incomingSignsServices.findAllPagination(page-1), HttpStatus.OK);
    }

    @PostMapping("/sign")
    public ResponseEntity<?> insert(@RequestBody IncomingSignsDtoPost incomingSignsDto) {
        incomingSignsServices.insert(incomingSignsDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/sign")
    public ResponseEntity<?> update(@RequestBody IncomingSignsDtoPost incomingSignsDto, @RequestParam int id) {
        incomingSignsServices.update(incomingSignsDto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
