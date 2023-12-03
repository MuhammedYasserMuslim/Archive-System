package com.spring.controller;

import com.spring.model.dto.ExportDto;
import com.spring.services.ExportServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Export Apis")
@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ExportController {

    private final ExportServices exportServices;


    @GetMapping("/count")
    @Operation(summary = "Get Exports Count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(exportServices.count());
    }

    @GetMapping("/count-current")
    @Operation(summary = "Get Exports today Count")
    public ResponseEntity<Long> countCurrent() {
        return ResponseEntity.ok(exportServices.countCurrent());
    }

    @GetMapping("/exports")
    @Operation(summary = "Get All Exports")
    public ResponseEntity<List<ExportDto>> findAll() {
        return ResponseEntity.ok(exportServices.findAll());
    }

    @GetMapping("/export-id")
    @Operation(summary = "Get Exports By Id")
    public ResponseEntity<ExportDto> findById(@RequestParam Short id) {
        return ResponseEntity.ok(exportServices.findById(id));
    }

    @GetMapping("/export-summary")
    @Operation(summary = "Get Export By Summary")
    public  ResponseEntity<List<ExportDto>> findBySummary(@RequestParam String summary) {
        return ResponseEntity.ok(exportServices.findBySummary(summary));
    }
    @GetMapping("/export-date")
    @Operation(summary = "Get Today Export")
    public ResponseEntity<List<ExportDto>> findByDate(){
        return ResponseEntity.ok(exportServices.findByDate());
    }

    @GetMapping("/export-archive")
    @Operation(summary = "Get Export By Archive File Id")
    public ResponseEntity<List<ExportDto>> findByArchiveFile(@RequestParam short id){
        return ResponseEntity.ok(exportServices.findByArchiveFile(id));
    }

    @PostMapping("/export")
    @Operation(summary = "Add Export ")
    public ResponseEntity<ExportDto> insert(@RequestBody ExportDto dto) {
        exportServices.insert(dto);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/exports")
    @Operation(summary = "Add List Of Exports ")
    public ResponseEntity<List<ExportDto>> insertAll(@RequestBody List<ExportDto> dtos){
        exportServices.insertAll(dtos);
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/export")
    @Operation(summary = "Delete Export ")
    public void deleteById(@RequestParam Short id){
        exportServices.deleteById(id);
    }




}
