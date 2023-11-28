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

    @GetMapping("/exports")
    @Operation(summary = "Get All Exports")
    public List<ExportDto> findAll() {
        return exportServices.findAll();
    }

    @GetMapping("/export-id")
    @Operation(summary = "Get Exports By Id")
    public ExportDto findById(@RequestParam Short id) {
        return exportServices.findById(id);
    }

    @GetMapping("/export-summary")
    @Operation(summary = "Get Archive File By Summary")
    public List<ExportDto> findBySummary(@RequestParam String summary) {
        return exportServices.findBySummary(summary);
    }

    @PostMapping("/export")
    @Operation(summary = "Add Export ")
    public ResponseEntity<?> insert(@RequestBody ExportDto dto) {
        exportServices.insert(dto);
        return ResponseEntity.ok(dto);
    }

}
