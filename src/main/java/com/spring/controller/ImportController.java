package com.spring.controller;

import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.services.ImportServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Import Apis")
@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
public class ImportController {

    private final ImportServices importServices;

    @GetMapping("/count")
    @Operation(summary = "Get Import Count")
//    @PreAuthorize("hasRole('MANGER')")
    public ResponseEntity<Integer> count() {
        return ResponseEntity.ok(importServices.count());
    }

    @GetMapping("/count-current")
    @Operation(summary = "Get Import today Count")
    public ResponseEntity<Integer> countCurrent() {
        return ResponseEntity.ok(importServices.countCurrent());
    }

    @GetMapping("/count-important")
//    @PreAuthorize(" hasRole('ADMIN') ")
    @Operation(summary = "Get count Important Imports  (عدد الخطابات الهامة )")
    public ResponseEntity<Integer> countImportantFile() {
        return ResponseEntity.ok(importServices.countImportantFile());
    }

    @GetMapping("/count-response-date-is-time")
    @Operation(summary = "Get count Import time has come (عدد خطابات حان موعدها)")
    public ResponseEntity<Integer> countItIsTime() {
        return ResponseEntity.ok(importServices.countItIsTime());
    }

    @GetMapping("/count-response-date-passed")
    @Operation(summary = "Get count Import time has passed (عدد خطابات ذهب موعدها)")
    public ResponseEntity<Integer> countPassedDate() {
        return ResponseEntity.ok(importServices.countPassedDate());
    }

    @GetMapping("/count-response-date-not-time")
    @Operation(summary = "Get count Import time has not come (عدد خطابات لم يحن  موعدها)")
    public ResponseEntity<Integer> countItIsNotTime() {
        return ResponseEntity.ok(importServices.countItIsNotTime());
    }


    @GetMapping("/all-imports")
    @Operation(summary = "Get All Imports")
    public ResponseEntity<List<ImportDto>> findAll() {
        return ResponseEntity.ok(importServices.findAll());
    }

    @GetMapping("/imports")
    @Operation(summary = "Get All Imports")
    public ResponseEntity<List<ImportDto>> findByYear() {
        return ResponseEntity.ok(importServices.findByYear());
    }

    @GetMapping("/imports-pagination")
    public ResponseEntity<ImportDto> findAllPagination(@RequestParam int page) {
        return new ResponseEntity<>(importServices.findAllPagination(page - 1), HttpStatus.OK);
    }

    @GetMapping("/import-id")
    @Operation(summary = "Get Imports By Id")
    public ResponseEntity<ImportDto> findById(@RequestParam int id) {
        return ResponseEntity.ok(importServices.findById(id));
    }


    @GetMapping("/import-date")
    @Operation(summary = "Get Today Imports")
    private ResponseEntity<List<ImportDto>> findByIncomeDate() {
        return new ResponseEntity<>(importServices.findByIncomeDate(), HttpStatus.OK);
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
    public ResponseEntity<?> insert(@RequestBody ImportDtoPost dto) {
        importServices.insert(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/import-id")
    @Operation(summary = "Update Import By Id")
    public ResponseEntity<?> update(@RequestBody ImportDtoPost dto, @RequestParam int id) {
        importServices.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/import-add-response")
    @Operation(summary = "Update Import")
    public ResponseEntity<?> addResponse(@RequestBody ExportDtoPost dto, @RequestParam int id) {
        importServices.addResponse(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/count-import-by-year")
    public ResponseEntity<Integer> findByYearDate(@RequestParam String year) {
        return new ResponseEntity<>(importServices.findByYearDate(year), HttpStatus.OK);
    }


}
