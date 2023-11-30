package com.spring.services;

import com.spring.model.dto.ArchiveFileDto;
import com.spring.model.dto.ExportDto;
import com.spring.model.dto.ImportDto;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.mapper.ArchiveFileMapper;
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
    private final ArchiveFileServices archiveFileServices;
    private final ArchiveFileMapper archiveFileMapper;
    private final ImportMapper importMapper;

    public Long count() {
        return importRepository.count();
    }

    public Long countCurrent() {
        return importRepository.countCurrent();
    }

    public Long countExpectResponseDate() {
        return importRepository.countExpectResponseDate();
    }

    public List<ImportDto> findAll() {
        List<Import> imports = importRepository.findAll();
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }
        return dtos;
    }

    public ImportDto findById(Short id) {
        Import importa = importRepository.findById(id).get();
        return importMapper.mapToDto(importa);
    }


    public List<ImportDto> findBySummary(String summary) {
        List<Import> imports = importRepository.findBySummaryContaining(summary);
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }
        return dtos;
    }

    public List<ImportDto> findByIncomeDate() {
        List<Import> imports = importRepository.findByIncomeDate();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }
        return dtos;

    }

    public List<ImportDto> findByExpectResponseDate() {
        List<Import> imports = importRepository.findByExpectResponseDate();
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }

        return dtos;
    }

    public List<ImportDto> findByArchiveFile(short id) {
        ArchiveFileDto dto = archiveFileServices.findById(id);
        List<Import> imports = importRepository.findByArchiveFile(archiveFileMapper.mapToEntity(dto));
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }
        return dtos;
    }


    public void insert(ImportDto dto) {
        Import importa = importMapper.mapToEntity(dto);
        importRepository.save(importa);
    }

    public void deleteById(Short id) {
        importRepository.deleteById(id);
    }
}
