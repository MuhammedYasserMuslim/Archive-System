package com.spring.controller;

import com.spring.model.dto.exports.ExportDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.services.ImportServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  //  @PreAuthorize("hasRole('MANGER') or hasRole('ADMIN')")
    public ResponseEntity<Long> count() {
        return ResponseEntity.ok(importServices.count());
    }

    @GetMapping("/count-current")
    @Operation(summary = "Get Import today Count")
    public ResponseEntity<Long> countCurrent() {
        return ResponseEntity.ok(importServices.countCurrent());
    }

    @GetMapping("/count-important")
   // @PreAuthorize(" hasRole('ADMIN') ")
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
    @GetMapping("/imports-pagination")
    public ResponseEntity<List<ImportDto>> findAllPagination(@RequestParam int page) {
        return new ResponseEntity<>(importServices.findAllPagination(page-1), HttpStatus.OK);
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
    public void insert(@RequestBody ImportDtoPost dto) {
        importServices.insert(dto);
    }

    @PutMapping("/import")
    @Operation(summary = "Update Import")
    public void update(@RequestBody ImportDtoPost dto) {
        importServices.update(dto);
    }

    @PutMapping("/import-id")
    @Operation(summary = "Update Import By Id")
    public void update(@RequestBody ImportDtoPost dto ,@RequestParam Short id) {
        importServices.update(dto,id);
    }

    @PutMapping("/import-add-response")
    @Operation(summary = "Update Import")
    public void addResponse(@RequestBody ExportDtoPost dto, @RequestParam short id) {
        importServices.addResponse(dto,id);
    }


}
