package com.spring.controller;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.services.ArchiveFileServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Archive File Apis")
@RestController
@RequiredArgsConstructor
@RequestMapping("/archive")
@CrossOrigin("http://localhost:4200")
public class ArchiveFileController {

    private final ArchiveFileServices archiveFileServices;

    @GetMapping("/count")
    @Operation(summary = "Get Archive Files Count ")
   // @PreAuthorize("hasRole('MANGER') or hasRole('ADMIN') or hasRole('USER')")
    public Long count() {
        return archiveFileServices.count();
    }

    @GetMapping("/archives")
    @Operation(summary = "Get All Archive Files")
    public List<ArchiveFileDto> findAll() {
        return archiveFileServices.findAll();
    }

    @GetMapping("/archive-id")
    @Operation(summary = "Get Archive File By Id")
    public ArchiveFileDto findById(@RequestParam short id) {
        return archiveFileServices.findById(id);
    }

    @GetMapping("/archive-name")
    @Operation(summary = "Get Archive File By Name")
    public List<ArchiveFileDto> findByNameLike(String name) {
        return archiveFileServices.findByNameContaining(name);
    }

    @GetMapping("/archive-num")
    @Operation(summary = "Get Archive File By Number and Num")
    public ArchiveFileDto findByTypeNumberAndNum(@RequestParam Byte typeNumber, @RequestParam Short num) {

        return archiveFileServices.findByTypeNumberAndNum(typeNumber, num);
    }

    @PostMapping("/archive")
    @Operation(summary = "Add Archive File")
    public void insert(@RequestBody ArchiveFileDto archiveFile) {
        archiveFileServices.insert(archiveFile);
    }

    @PostMapping("/archives")
    @Operation(summary = "Add List Of  Archive Files")
    public void saveAll(@RequestBody List<ArchiveFileDto> archiveFiles) {
        archiveFileServices.saveAll(archiveFiles);
    }

    @PutMapping("/archive")
    @Operation(summary = "Update Archive Files")
    public void update(@RequestBody ArchiveFileDto archiveFile) {
        archiveFileServices.update(archiveFile);
    }


}
