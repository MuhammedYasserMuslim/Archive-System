package com.spring.controller;

import com.spring.model.dto.deandecisions.DeanDecisionsDto;
import com.spring.model.dto.deandecisions.DeanDecisionsDtoPost;
import com.spring.services.DeanDecisionsServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Dean Decisions Apis")
@RestController
@RequestMapping("/dean-decisions")
@RequiredArgsConstructor
public class DeanDecisionsController {

    private final DeanDecisionsServices deanDecisionsServices;

    @GetMapping("/all-decisions")
    public ResponseEntity<List<DeanDecisionsDto>> findAll() {
        return new ResponseEntity<>(deanDecisionsServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        return new ResponseEntity<>(deanDecisionsServices.count() , HttpStatus.OK);
    }

    @GetMapping("/decisions")
    public ResponseEntity<List<DeanDecisionsDto>> findByYear() {
        return new ResponseEntity<>(deanDecisionsServices.findByYear(), HttpStatus.OK);
    }

    @GetMapping("/decision")
    public ResponseEntity<DeanDecisionsDto> findById(@RequestParam Integer id) {
        return new ResponseEntity<>(deanDecisionsServices.findById(id), HttpStatus.OK);
    }

    @GetMapping("/decisions-pagination")
    public ResponseEntity<DeanDecisionsDto> findAllPagination(@RequestParam int page) {
        return new ResponseEntity<>(deanDecisionsServices.findAllPaginationByYear(page - 1), HttpStatus.OK);
    }

    @PostMapping("/decision")
    public ResponseEntity<?> insert(@RequestBody DeanDecisionsDtoPost dto) {
        deanDecisionsServices.insert(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/decision")
    public ResponseEntity<?> update(@RequestBody DeanDecisionsDtoPost dto, @RequestParam int id) {
        deanDecisionsServices.update(dto, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
