package com.spring.services;

import com.spring.model.dto.ExportDto;
import com.spring.model.entity.Export;
import com.spring.model.mapper.ExportMapper;
import com.spring.repository.ExportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExportServices {

    private final ExportRepository exportRepository;
    private final ExportMapper exportMapper;


    public Long count(){
        return exportRepository.count();
    }

    public List<ExportDto> findAll(){
        List<Export> exports= exportRepository.findAll();
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }
        return dtos;
    }
    public ExportDto findById(Short id){
        Export export = exportRepository.findById(id).get();
        return exportMapper.mapToDto(export);


    }
    public List<ExportDto> findBySummary(String summary){
        List<Export> exports= exportRepository.findBySummaryContaining(summary);
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }
        return dtos;
    }


    public void insert(ExportDto dto){
        Export export =exportMapper.mapToEntity(dto);
        exportRepository.save(export);
    }



}
