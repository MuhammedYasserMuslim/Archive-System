package com.spring.controller;

import com.spring.model.dto.exports.ExportDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.services.ExportServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Export Apis")
@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExportController {

    private final ExportServices exportServices;


    @GetMapping("/count")
    @Operation(summary = "Show Exports Count")
    public ResponseEntity<Integer> count() {
        return ResponseEntity.ok(exportServices.count());
    }

    @GetMapping("/count-current")
    @Operation(summary = "Show Exports today Count")
    public ResponseEntity<Integer> countCurrent() {
        return new ResponseEntity<>(exportServices.countCurrent(), HttpStatus.OK);
    }

    @GetMapping("/exports")
    @Operation(summary = "Show All Exports in Current Year")
    public ResponseEntity<List<ExportDto>> findByYear() {
        return new ResponseEntity<>(exportServices.findByYear(), HttpStatus.OK);
    }

    @GetMapping("/all-exports")
    @Operation(summary = "Show All Exports ")
    public ResponseEntity<List<ExportDto>> findAll() {
        return new ResponseEntity<>(exportServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/exports-pagination")
    @Operation(summary = "Show All Exports in Current Year For Pagination")
    public ResponseEntity<ExportDto> findAllPagination(@RequestParam int page) {
        return new ResponseEntity<>(exportServices.findAllPaginationByYear(page - 1), HttpStatus.OK);
    }

    @GetMapping("/export-id")
    @Operation(summary = "Show Exports By Id")
    public ResponseEntity<ExportDto> findById(@RequestParam int id) {
        return ResponseEntity.ok(exportServices.findById(id));
    }


    @GetMapping("/export-date")
    @Operation(summary = "Show Today Export")
    public ResponseEntity<List<ExportDto>> findByDate() {
        return ResponseEntity.ok(exportServices.findByDate());
    }

    @GetMapping("/export-archive")
    @Operation(summary = "Show Export By Archive File Id")
    public ResponseEntity<List<ExportDto>> findByArchiveFile(@RequestParam short id) {
        return ResponseEntity.ok(exportServices.findByArchiveFile(id));
    }

    @PostMapping("/export")
    @Operation(summary = "Add Export File ")
    public ResponseEntity<?> insert(@RequestBody ExportDtoPost dto) {
        exportServices.insert(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/export-id")
    @Operation(summary = "Update Export By Id ")
    public ResponseEntity<?> update(@RequestBody ExportDtoPost dto, @RequestParam int id) {
        exportServices.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/export-add-urgent")
    @Operation(summary = "Add  Urgent ")
    public ResponseEntity<ExportDtoPost> addUrgent(@RequestBody ExportDtoPost dto, @RequestParam int id) {
        exportServices.addUrgent(dto, id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/export-add-response")
    @Operation(summary = "Add  Response ")
    public ResponseEntity<ImportDtoPost> addResponse(@RequestBody ImportDtoPost dto, @RequestParam int id) {
        exportServices.addResponse(dto, id);
        return ResponseEntity.ok(dto);
    }

    // year = 2024/2025 you should take 2024 only
    @GetMapping("/count-export-by-year")
    public ResponseEntity<Integer> findByYearDate(@RequestParam String year) {
        return new ResponseEntity<>(exportServices.findByYearDate(year.substring(0,5)), HttpStatus.OK);
    }


}
