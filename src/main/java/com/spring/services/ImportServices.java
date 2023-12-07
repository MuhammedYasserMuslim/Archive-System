package com.spring.services;

import com.spring.exception.ConflictException;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.model.mapper.ImportMapper;
import com.spring.model.mapper.ImportPostDto;
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



    public long count() {
        return importRepository.count();
    }

    public Long countCurrent() {
        return importRepository.countCurrent();
    }

    public Long countItIsTime() {
        return importRepository.countItIsTime();
    }

    public Long countPassedDate() {
        return importRepository.countPassedDate();
    }

    public Long countImportantFile() {
        return importRepository.countImportantFile();
    }

    public Long countItIsNotTime(){
        return importRepository.countItIsNotTime();
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

    public List<ImportDto> findItIsTime() {
        List<Import> imports = importRepository.findItIsTime();
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }

        return dtos;
    }

    public List<ImportDto>findItIsNotTime(){
        List<Import> imports = importRepository.findItIsNotTime();
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }

        return dtos;
    }

    public List<ImportDto> findPassedDate() {
        List<Import> imports = importRepository.findPassedDate();
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }

        return dtos;
    }

    public List<ImportDto> findImportantFile() {
        List<Import> imports = importRepository.findImportantFile();
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


    public void insert(ImportDtoPost dto) {
        dto.setTypeNumber((byte) 1);
        Import importa = importMapper.mapToEntity(dto);
        importa.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum(importa.getArchiveFile().getTypeNumber(),importa.getArchiveFile().getNum())));

        importRepository.save(importa);
    }

    public void update(ImportDtoPost dto) {
        Import importa = importMapper.mapToEntity(dto);
        dto.setTypeNumber((byte) 1);
        importa.setId(dto.getId());
        importa.setSender(dto.getSender());
        importa.setIncomingLetterNumber(dto.getIncomingLetterNumber());
        importa.setIncomingLetterDate(dto.getIncomingLetterDate());
        importa.setSummary(dto.getSummary());
        importa.setRecipientName(dto.getRecipientName());
        importa.setRecipientDate(dto.getRecipientDate());
        importa.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
                ((byte)1,
                        importa.getArchiveFile().getNum())));
        importa.setIncomeDate(importRepository.findById(dto.getId()).get().getIncomeDate());
        importa.setExport(importRepository.findById(dto.getId()).get().getExport());
        importa.setNumberOfAttachments(importRepository.findById(dto.getId()).get().getNumberOfAttachments());
        importa.setCreatedBy(importRepository.findById(dto.getId()).get().getCreatedBy());
        importa.setCreatedDate(importRepository.findById(dto.getId()).get().getCreatedDate());

        importRepository.save(importa);
    }




}
