package com.spring.controller;

import com.spring.model.entity.Image;
import com.spring.services.FileUploadService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
@Tag(name = "Image Apis")
public class FileUploadController {

    private final FileUploadService fileUploadService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam int id, @RequestParam String pathType
            , @RequestParam MultipartFile file) {
        String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType);
        return ResponseEntity.ok(fileName);
    }

    @PostMapping("/multipleFiles")
    public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,
                                                 @RequestParam("id") int id,
                                                 @RequestParam String pathType) {
        List<String> multipartFiles = new ArrayList<>();
        for (MultipartFile file : files){
            uploadFile(id, pathType, file);
            multipartFiles.add(file.getOriginalFilename());
        }
        return ResponseEntity.ok(multipartFiles);
    }

    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
        byte[] imageData = fileUploadService.getFileFromFileSystem(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

    @GetMapping("/image")
    public ResponseEntity<Image> findByImagePath(@RequestParam String imagePath) {
        return new ResponseEntity<>(fileUploadService.findByImagePath(imagePath), HttpStatus.OK);
    }

    @DeleteMapping("/image")
    public ResponseEntity<?> deleteByImagePath(@RequestParam String imagePath) {
        fileUploadService.deleteByImagePath(imagePath);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
