package com.spring.controller;

import com.spring.model.dto.ImportDto;
import com.spring.services.ImportServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Import Apis")
@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ImportController {

    private final ImportServices importServices;

    @GetMapping("/count")
    @Operation(summary = "Get Import Count")
    public Long count(){
        return importServices.count();
    }

    @GetMapping("/imports")
    @Operation(summary = "Get All Imports")
    public List<ImportDto> findAll() {
        return importServices.findAll();
    }

    @GetMapping("/import-id")
    @Operation(summary = "Get Imports By Id")
    public ImportDto findById(@RequestParam Short id) {
        return importServices.findById(id);
    }

    @GetMapping("/import-summary")
    @Operation(summary = "Get Imports By Summary")
    public List<ImportDto> findBySummary(@RequestParam String summary) {
        return importServices.findBySummary(summary);
    }

    @PostMapping("/import")
    @Operation(summary = "Add Import ")
    public ResponseEntity<?> insert(@RequestBody ImportDto dto) {
        importServices.insert(dto);
        return ResponseEntity.ok(dto);
    }


}
