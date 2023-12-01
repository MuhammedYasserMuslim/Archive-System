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
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(importServices.count());
    }

    @GetMapping("/count-current")
    @Operation(summary = "Get Import today Count")
    public ResponseEntity<Long> countCurrent() {
        return ResponseEntity.ok(importServices.countCurrent());
    }

    @GetMapping("/count-important")
    @Operation(summary = "Get count Important Imports  (عدد الخطابات الهامة )")
    public ResponseEntity<Long> countImportantFile() {
        return ResponseEntity.ok(importServices.countImportantFile());
    }

    @GetMapping("/count-response-date-is-time")
    @Operation(summary = "Get count Import time has come (عدد خطابات حان موعدها)")
    public ResponseEntity<Long> countItIsTime() {
        return ResponseEntity.ok(importServices.countItIsTime());
    }

    @GetMapping("/count-response-date-passed")
    @Operation(summary = "Get count Import time has passed (عدد خطابات ذهب موعدها)")
    public ResponseEntity<Long> countPassedDate() {
        return ResponseEntity.ok(importServices.countPassedDate());
    }

    @GetMapping("/count-response-date-not-time")
    @Operation(summary = "Get count Import time has not come (عدد خطابات لم يحن  موعدها)")
    public ResponseEntity<Long> countItIsNotTime() {
        return ResponseEntity.ok(importServices.countItIsNotTime());
    }


    @GetMapping("/imports")
    @Operation(summary = "Get All Imports")
    public ResponseEntity<List<ImportDto>> findAll() {
        return ResponseEntity.ok(importServices.findAll());
    }

    @GetMapping("/import-id")
    @Operation(summary = "Get Imports By Id")
    public ResponseEntity<ImportDto> findById(@RequestParam Short id) {
        return ResponseEntity.ok(importServices.findById(id));
    }

    @GetMapping("/import-summary")
    @Operation(summary = "Get Imports By Summary")
    public ResponseEntity<List<ImportDto>> findBySummary(@RequestParam String summary) {
        return ResponseEntity.ok(importServices.findBySummary(summary));
    }

    @GetMapping("/import-date")
    @Operation(summary = "Get Today Imports")
    private List<ImportDto> findByIncomeDate() {
        return importServices.findByIncomeDate();
    }

    @GetMapping("/import-archive")
    @Operation(summary = "Get Import By Archive File Id")
    public ResponseEntity<List<ImportDto>> findByArchiveFile(@RequestParam short id) {
        return ResponseEntity.ok(importServices.findByArchiveFile(id));
    }

    @GetMapping("/import-important")
    @Operation(summary = "Get  important Import File ( الملفات الهامة)")
    public ResponseEntity<List<ImportDto>> findImportantFile() {
        return ResponseEntity.ok(importServices.findImportantFile());
    }

    @GetMapping("/import-response-date-is-time")
    @Operation(summary = "Get  Import time has come ( خطابات حان موعدها)")
    public ResponseEntity<List<ImportDto>> findItIsTime() {
        return ResponseEntity.ok(importServices.findItIsTime());
    }

    @GetMapping("/import-response-date-not-time")
    @Operation(summary = "Get  Import time has Not come  ( خطابات  لم يحن موعدها)")
    public ResponseEntity<List<ImportDto>> findItIsNotTime() {
        return ResponseEntity.ok(importServices.findItIsNotTime());
    }

    @GetMapping("/import-response-date-passed")
    @Operation(summary = "Get  Import time has passed ( خطابات ذهب موعدها)")
    public ResponseEntity<List<ImportDto>> findPassedDate() {
        return ResponseEntity.ok(importServices.findPassedDate());
    }


    @PostMapping("/import")
    @Operation(summary = "Add Import ")
    public ResponseEntity<?> insert(@RequestBody ImportDto dto) {
        importServices.insert(dto);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/import")
    @Operation(summary = "Delete Import ")
    public void deleteById(@RequestParam Short id) {
        importServices.deleteById(id);
    }


}
