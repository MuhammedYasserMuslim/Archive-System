package com.spring.controller;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.services.ArchiveFileServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Archive File Apis")
@RestController
@RequiredArgsConstructor
@RequestMapping("/archive")
public class ArchiveFileController {

    private final ArchiveFileServices archiveFileServices;

    @GetMapping("/count")
    @Operation(summary = "Show Archive Files Count ")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(archiveFileServices.count(), HttpStatus.OK);
    }

    @GetMapping("/archives")
    @Operation(summary = "Show All Archive Files")
    public ResponseEntity<List<ArchiveFileDto>> findAll() {
        return new ResponseEntity<>(archiveFileServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/archive-id")
    @Operation(summary = "Show Archive File By Id")
    public ResponseEntity<ArchiveFileDto> findById(@RequestParam short id) {
        return new ResponseEntity<>(archiveFileServices.findById(id), HttpStatus.OK);
    }

    @GetMapping("/archive-name")
    @Operation(summary = "Show Archive File By Name")
    public ResponseEntity<List<ArchiveFileDto>> findByNameLike(String name) {
        return new ResponseEntity<>(archiveFileServices.findByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/archive-num")
    @Operation(summary = "Show Archive File By Number and Num")
    public ResponseEntity<ArchiveFileDto> findByTypeNumberAndNum(@RequestParam Byte typeNumber, @RequestParam Short num) {

        return new ResponseEntity<>(archiveFileServices.findByTypeNumberAndNum(typeNumber, num), HttpStatus.OK);
    }

    @PostMapping("/archive")
    @Operation(summary = "Add Archive File")
    public ResponseEntity<?> insert(@RequestBody ArchiveFileDto archiveFile) {
        archiveFileServices.insert(archiveFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/archive")
    @Operation(summary = "Update Archive Files")
    public ResponseEntity<?> update(@RequestBody ArchiveFileDto archiveFile) {
        archiveFileServices.update(archiveFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/archive")
    public ResponseEntity<?> delete(@RequestParam Byte typeNumber,@RequestParam Short num) {
        archiveFileServices.deleteByTypeNumberAndNum(typeNumber, num);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
