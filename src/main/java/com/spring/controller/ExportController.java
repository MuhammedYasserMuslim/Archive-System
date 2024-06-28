package com.spring.controller;

import com.spring.model.dto.exports.AllExportDto;
import com.spring.model.dto.exports.ExportDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.services.ExportServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Export")
@RestController
@RequestMapping("/export")
@RequiredArgsConstructor
public class ExportController {

    private final ExportServices exportServices;


    @GetMapping("/count")
    public ResponseEntity<Integer> count() {
        return ResponseEntity.ok(exportServices.count());
    }

    @GetMapping("/count-current")
    public ResponseEntity<Integer> countCurrent() {
        return ResponseEntity.ok(exportServices.countCurrent());
    }


    @GetMapping("/exports")
    public ResponseEntity<List<ExportDto>> findByYear() {
        return new ResponseEntity<>(exportServices.findByYear(), HttpStatus.OK);
    }

    @GetMapping("/all-exports")
    public ResponseEntity<List<ExportDto>> findAll() {
        return new ResponseEntity<>(exportServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/exports-pagination")
    public ResponseEntity<ExportDto> findAllPagination(@RequestParam int page) {
        return new ResponseEntity<>(exportServices.findAllPaginationByYear(page - 1), HttpStatus.OK);
    }

    @GetMapping("/export-id")
    public ResponseEntity<ExportDto> findById(@RequestParam int id) {
        return ResponseEntity.ok(exportServices.findById(id));
    }

    @GetMapping("/export-date")
    public ResponseEntity<List<ExportDto>> findByDate() {
        return ResponseEntity.ok(exportServices.findByDate());
    }

    @GetMapping("/export-archive")
    public ResponseEntity<List<ExportDto>> findByArchiveFile(@RequestParam short id) {
        return ResponseEntity.ok(exportServices.findByArchiveFile(id));
    }

    @PostMapping("/export")
    public ResponseEntity<?> insert(@RequestBody ExportDtoPost dto) {
        exportServices.insert(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/export-id")
    public ResponseEntity<?> update(@RequestBody ExportDtoPost dto, @RequestParam int id) {
        exportServices.update(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/export-add-urgent")
    public ResponseEntity<ExportDtoPost> addUrgent(@RequestBody ExportDtoPost dto, @RequestParam int id) {
        exportServices.addUrgent(dto, id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/export-add-response")
    public ResponseEntity<ImportDtoPost> addResponse(@RequestBody ImportDtoPost dto, @RequestParam int id) {
        exportServices.addResponse(dto, id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/count-export-by-year")
    public ResponseEntity<Integer> findByYearDate(@RequestParam String year) throws StringIndexOutOfBoundsException {
        return new ResponseEntity<>(exportServices.findByYearDate(year.substring(5, 9)), HttpStatus.OK);
    }

    @PutMapping("/convert-to-special")
    public ResponseEntity<?> convertToSpecial(@RequestParam int id, @RequestBody Body body) {
        exportServices.convertToSpecial(id, body.num());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public record Body(short num) {
    }

}
