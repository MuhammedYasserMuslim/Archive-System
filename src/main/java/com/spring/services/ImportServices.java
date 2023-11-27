package com.spring.services;

import com.spring.model.dto.ExportDto;
import com.spring.model.dto.ImportDto;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.mapper.ImportMapper;
import com.spring.repository.ImportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportServices {

    private final ImportRepository importRepository;
    private final ImportMapper importMapper;

    public List<ImportDto> findAll(){
        List<Import>imports = importRepository.findAll();
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }
        return dtos;
    }


    public ImportDto findById(Short id){
        Import importa = importRepository.findById(id).get();
        ImportDto dto = importMapper.mapToDto(importa);
        return dto;
    }
    public void insert(ImportDto dto){
        Import importa =importMapper.mapToEntity(dto);
        importRepository.save(importa);
    }


    public void insertEntity(Import importa){
        importRepository.save(importa);
    }

    public List<ImportDto> findBySummary(String summary){
        List<Import>imports = importRepository.findBySummaryContaining(summary);
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }
        return dtos;
    }
}
