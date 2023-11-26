package com.spring.controller;

import com.spring.model.dto.ExportDto;
import com.spring.model.entity.Export;
import com.spring.services.ExportServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
    public List<ExportDto> findAll(){
        return exportServices.findAll();
    }
    @GetMapping("/export")
    public ExportDto findById(@RequestParam Short id){
        return exportServices.findById(id);
    }
    @PostMapping("/export")
    public void insert(ExportDto dto){
        exportServices.insert(dto);
    }
}
