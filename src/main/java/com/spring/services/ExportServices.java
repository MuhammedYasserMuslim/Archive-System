package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.ArchiveFileDto;
import com.spring.model.dto.ExportDto;
import com.spring.model.entity.Export;
import com.spring.model.mapper.ArchiveFileMapper;
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
    private final ArchiveFileServices archiveFileServices;
    private final ArchiveFileMapper archiveFileMapper;
    private final ExportMapper exportMapper;


    public Long count() {
        return exportRepository.count();
    }

    public Long countCurrent() {
        return exportRepository.countCurrent();
    }

    public List<ExportDto> findAll() {
        List<Export> exports = exportRepository.findAll();
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }
        return dtos;
    }

    public ExportDto findById(Short id) {

        if (exportRepository.findById(id).isPresent()) {
            Export export = exportRepository.findById(id).get();
            return exportMapper.mapToDto(export);
        } else
            throw new RecordNotFountException("File With id " + id + " Not Found");


    }

    public List<ExportDto> findBySummary(String summary) {
        List<Export> exports = exportRepository.findBySummaryContaining(summary);
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }
        return dtos;
    }

    public List<ExportDto> findByDate() {

        List<Export> exports = exportRepository.findByDate();
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }
        return dtos;
    }

    public List<ExportDto> findByArchiveFile(short id) {
        ArchiveFileDto dto = archiveFileServices.findById(id);
        List<Export> exports = exportRepository.findByArchiveFile(archiveFileMapper.mapToEntity(dto));
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }

        return dtos;
    }

    public void insert(ExportDto dto) {
        Export export = exportMapper.mapToEntity(dto);
        exportRepository.save(export);
    }

    public void insertAll(List<ExportDto> dtos) {
        List<Export> exports = new ArrayList<>();
        for (ExportDto dto : dtos) {
            exports.add(exportMapper.mapToEntity(dto));
        }
        exportRepository.saveAll(exports);
    }

    public void deleteById(Short id) {
        exportRepository.deleteById(id);
    }


}
