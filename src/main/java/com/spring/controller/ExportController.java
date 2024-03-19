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
    @Operation(summary = "Get Exports Count")
    public ResponseEntity<Integer> count() {
        return ResponseEntity.ok(exportServices.count());
    }

    @GetMapping("/count-current")
    @Operation(summary = "Get Exports today Count")
    public ResponseEntity<Integer> countCurrent() {
        return new ResponseEntity<>(exportServices.countCurrent(), HttpStatus.OK);
    }

    @GetMapping("/exports")
    @Operation(summary = "Get All Exports")
    public ResponseEntity<List<ExportDto>> findByYear() {
        return new ResponseEntity<>(exportServices.findByYear(), HttpStatus.OK);
    }

    @GetMapping("/all-exports")
    public ResponseEntity<List<ExportDto>> findAll() {
        return new ResponseEntity<>(exportServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/exports-pagination")
    public ResponseEntity<List<ExportDto>> findAllPagination(@RequestParam int page) {
        return new ResponseEntity<>(exportServices.findAllPaginationByYear(page - 1), HttpStatus.OK);
    }

    @GetMapping("/export-id")
    @Operation(summary = "Get Exports By Id")
    public ResponseEntity<ExportDto> findById(@RequestParam int id) {
        return ResponseEntity.ok(exportServices.findById(id));
    }


    @GetMapping("/export-date")
    @Operation(summary = "Get Today Export")
    public ResponseEntity<List<ExportDto>> findByDate() {
        return ResponseEntity.ok(exportServices.findByDate());
    }

    @GetMapping("/export-archive")
    @Operation(summary = "Get Export By Archive File Id")
    public ResponseEntity<List<ExportDto>> findByArchiveFile(@RequestParam short id) {
        return ResponseEntity.ok(exportServices.findByArchiveFile(id));
    }

    @PostMapping("/export")
    @Operation(summary = "Add Export ")
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

    @GetMapping("/count-export-by-year")
    public ResponseEntity<Integer> findByYearDate(@RequestParam String year) {
        return new ResponseEntity<>(exportServices.findByYearDate(year), HttpStatus.OK);
    }


}
