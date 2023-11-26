package com.spring.controller;

import com.spring.model.dto.ImportDto;
import com.spring.services.ImportServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Import Apis")
@RestController
@RequestMapping("/import")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class ImportController {

    private final ImportServices importServices;

    @GetMapping("/imports")
    public List<ImportDto> findAll(){
        return importServices.findAll();
    }
}
