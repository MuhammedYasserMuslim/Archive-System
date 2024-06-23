package com.spring.controller;

import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.services.ImportServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Import")
@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class ImportController {

    private final ImportServices importServices;

    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        return ResponseEntity.ok(importServices.count());
    }

    @GetMapping("/count-current")
    public ResponseEntity<Integer> countCurrent() {
        return ResponseEntity.ok(importServices.countCurrent());
    }

    @GetMapping("/count-important")
    public ResponseEntity<Integer> countImportantFile() {
        return ResponseEntity.ok(importServices.countImportantFile());
    }

    @GetMapping("/count-response-date-is-time")
    public ResponseEntity<Integer> countItIsTime() {
        return ResponseEntity.ok(importServices.countItIsTime());
    }

    @GetMapping("/count-response-date-passed")
    public ResponseEntity<Integer> countPassedDate() {
        return ResponseEntity.ok(importServices.countPassedDate());
    }

    @GetMapping("/count-response-date-not-time")
    public ResponseEntity<Integer> countItIsNotTime() {
        return ResponseEntity.ok(importServices.countItIsNotTime());
    }


    @GetMapping("/all-imports")
    public ResponseEntity<List<ImportDto>> findAll() {
        return ResponseEntity.ok(importServices.findAll());
    }

    @GetMapping("/imports")
    public ResponseEntity<List<ImportDto>> findByYear() {
        return ResponseEntity.ok(importServices.findByYear());
    }

    @GetMapping("/imports-pagination")
    public ResponseEntity<ImportDto> findAllPagination(@RequestParam int page) {
        return new ResponseEntity<>(importServices.findAllPagination(page - 1), HttpStatus.OK);
    }

    @GetMapping("/import-id")
    public ResponseEntity<ImportDto> findById(@RequestParam int id) {
        return ResponseEntity.ok(importServices.findById(id));
    }


    @GetMapping("/import-date")
    private ResponseEntity<List<ImportDto>> findByIncomeDate() {
        return new ResponseEntity<>(importServices.findByIncomeDate(), HttpStatus.OK);
    }

    @GetMapping("/import-archive")
    public ResponseEntity<List<ImportDto>> findByArchiveFile(@RequestParam short id) {
        return ResponseEntity.ok(importServices.findByArchiveFile(id));
    }

    @GetMapping("/import-important")
    public ResponseEntity<List<ImportDto>> findImportantFile() {
        return ResponseEntity.ok(importServices.findImportantFile());
    }

    @GetMapping("/import-response-date-is-time")
    public ResponseEntity<List<ImportDto>> findItIsTime() {
        return ResponseEntity.ok(importServices.findItIsTime());
    }

    @GetMapping("/import-response-date-not-time")
    public ResponseEntity<List<ImportDto>> findItIsNotTime() {
        return ResponseEntity.ok(importServices.findItIsNotTime());
    }

    @GetMapping("/import-response-date-passed")
    public ResponseEntity<List<ImportDto>> findPassedDate() {
        return ResponseEntity.ok(importServices.findPassedDate());
    }


    @PostMapping("/import")
    public ResponseEntity<?> insert(@RequestBody ImportDtoPost dto) {
        importServices.insert(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/imports")
    public ResponseEntity<?> insert(@RequestBody List<ImportDtoPost> dtos) {
        importServices.insertAll(dtos);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/import-id")
    public ResponseEntity<?> update(@RequestBody ImportDtoPost dto, @RequestParam int id) {
        importServices.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/import-add-response")
    public ResponseEntity<?> addResponse(@RequestBody ExportDtoPost dto, @RequestParam int id) {
        importServices.addResponse(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // year = 2024/2025 you should take 2024 only
    @GetMapping("/count-import-by-year")
    public ResponseEntity<Integer> findByYearDate(@RequestParam String year) {
        return new ResponseEntity<>(importServices.findByYearDate(year.substring(5, 9)), HttpStatus.OK);
    }

    @PutMapping("/convert-to-special")
    public ResponseEntity<?> changeArchiveFile(@RequestParam int id, @RequestBody Body body) {
        importServices.convertToSpecial(id, body.num());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public record Body(short num) {
    }
}
