package com.spring.controller;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.services.ArchiveFileServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Tag(name = "Archive File")
@RestController
@RequiredArgsConstructor
@RequestMapping("/archive")
public class ArchiveFileController {

    private final ArchiveFileServices archiveFileServices;

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(archiveFileServices.count(), HttpStatus.OK);
    }

    @GetMapping("/archives")
    public ResponseEntity<List<ArchiveFileDto>> findAll() {
        return new ResponseEntity<>(archiveFileServices.findAll(), HttpStatus.OK);
    }

    @GetMapping("/archive-id")
    public ResponseEntity<ArchiveFileDto> findById(@RequestParam short id) {
        return new ResponseEntity<>(archiveFileServices.findById(id), HttpStatus.OK);
    }

    @GetMapping("/archive-name")
    public ResponseEntity<List<ArchiveFileDto>> findByNameLike(String name) {
        return new ResponseEntity<>(archiveFileServices.findByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/archive-num")
    public ResponseEntity<ArchiveFileDto> findByTypeNumberAndNum(@RequestParam Byte typeNumber, @RequestParam Short num) {
        return new ResponseEntity<>(archiveFileServices.findByTypeNumberAndNum(typeNumber, num), HttpStatus.OK);
    }


    @GetMapping("/archive-type")
    public ResponseEntity<List<ArchiveFileDto>> findByTypeNumber(@RequestParam Byte typeNumber) {
        return new ResponseEntity<>(archiveFileServices.findByTypeNumber(typeNumber), HttpStatus.OK);
    }

    @PostMapping("/archive")
    public ResponseEntity<?> insert(@RequestBody ArchiveFileDto archiveFile) {
        archiveFileServices.insert(archiveFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/archives")
    public ResponseEntity<?> insert(@RequestBody List<ArchiveFileDto> archiveFiles) {
        for (ArchiveFileDto archiveFile : archiveFiles) {
            archiveFileServices.insert(archiveFile);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/archive")
    public ResponseEntity<?> update(@RequestBody ArchiveFileDto archiveFile) {
        archiveFileServices.update(archiveFile);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/archive-name")
    public ResponseEntity<?> updateName(@RequestBody Body body, @RequestParam short id) {
        archiveFileServices.updateName(body.name, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/archive")
    public ResponseEntity<?> delete(@RequestParam Byte typeNumber, @RequestParam Short num) {
        archiveFileServices.deleteByTypeNumberAndNum(typeNumber, num);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/close")
    public ResponseEntity<?> close() {
        archiveFileServices.closeArchiveFile();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/can-closed")
    public boolean canClose() {
        return LocalDateTime.now().getMonth().getValue() == 7 &&
                (LocalDateTime.now().getDayOfMonth() == 1 || LocalDateTime.now().getDayOfMonth() == 2 || LocalDateTime.now().getDayOfMonth() == 3 || LocalDateTime.now().getDayOfMonth() == 4 || LocalDateTime.now().getDayOfMonth() == 5 || LocalDateTime.now().getDayOfMonth() == 6 || LocalDateTime.now().getDayOfMonth() == 7);
    }

    public record Body(String name) {
    }


}
