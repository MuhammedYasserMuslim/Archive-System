package com.spring.services;

import com.spring.exception.ConflictException;
import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.model.mapper.ImportMapper;
import com.spring.repository.ImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImportServices {

    @Autowired
    private ImportRepository importRepository;
    @Autowired
    private ArchiveFileServices archiveFileServices;
    @Autowired
    private ArchiveFileMapper archiveFileMapper;
    @Autowired
    private ImportMapper importMapper;
    private final ExportServices exportServices;

    @Lazy
    public ImportServices(ExportServices exportServices) {
        this.exportServices = exportServices;
    }

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

    public Long countItIsNotTime() {
        return importRepository.countItIsNotTime();
    }

    //@Cacheable(value = "findAllImports", key = "#root.methodName")
    public List<ImportDto> findAll() {
        List<Import> imports = importRepository.findAllByOrderByIdDesc();
        List<ImportDto> dtos = new ArrayList<>();

        for (Import anImport : imports) {
            dtos.add(importMapper.mapToDto(anImport));
        }
        return dtos;
    }

    public ImportDto findById(Short id) {
        if (importRepository.findById(id).isPresent()) {
            Import importa = importRepository.findById(id).get();
            return importMapper.mapToDto(importa);
        } else throw new RecordNotFountException("Your search -" + id + " - did not match any documents.");
    }


    public List<ImportDto> findBySummary(String summary) {
        if (!importRepository.findBySummaryContaining(summary).isEmpty()) {
            List<Import> imports = importRepository.findBySummaryContaining(summary);
            List<ImportDto> dtos = new ArrayList<>();

            for (Import anImport : imports) {
                dtos.add(importMapper.mapToDto(anImport));
            }
            return dtos;
        } else throw new RecordNotFountException("Your search -" + summary + " - did not match any documents.");
    }

    public List<ImportDto> findByIncomeDate() {
        if (!importRepository.findByIncomeDate().isEmpty()) {
            List<Import> imports = importRepository.findByIncomeDate();
            List<ImportDto> dtos = new ArrayList<>();
            for (Import anImport : imports) {
                dtos.add(importMapper.mapToDto(anImport));
            }
            return dtos;
        } else

            throw new RecordNotFountException("There are no new files today.");
    }

    public List<ImportDto> findItIsTime() {
        if (!importRepository.findItIsTime().isEmpty()) {
            List<Import> imports = importRepository.findItIsTime();
            List<ImportDto> dtos = new ArrayList<>();

            for (Import anImport : imports) {
                dtos.add(importMapper.mapToDto(anImport));
            }
            return dtos;
        } else throw new RecordNotFountException("There are no new files time has come");

    }

    public List<ImportDto> findItIsNotTime() {
        if (!importRepository.findItIsNotTime().isEmpty()) {
            List<Import> imports = importRepository.findItIsNotTime();
            List<ImportDto> dtos = new ArrayList<>();

            for (Import anImport : imports) {
                dtos.add(importMapper.mapToDto(anImport));
            }

            return dtos;
        } else throw new RecordNotFountException("There are no  files time has not come");
    }

    public List<ImportDto> findPassedDate() {
        if (!importRepository.findPassedDate().isEmpty()) {
            List<Import> imports = importRepository.findPassedDate();
            List<ImportDto> dtos = new ArrayList<>();

            for (Import anImport : imports) {
                dtos.add(importMapper.mapToDto(anImport));
            }

            return dtos;
        } else throw new RecordNotFountException("There are no  files time has passed");
    }

    public List<ImportDto> findImportantFile() {
        if (!importRepository.findImportantFile().isEmpty()) {
            List<Import> imports = importRepository.findImportantFile();
            List<ImportDto> dtos = new ArrayList<>();

            for (Import anImport : imports) {
                dtos.add(importMapper.mapToDto(anImport));
            }

            return dtos;
        } else throw new RecordNotFountException("There are no Important files ");

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
        importa.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum(importa.getArchiveFile().getTypeNumber(), importa.getArchiveFile().getNum())));

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
                ((byte) 1,
                        importa.getArchiveFile().getNum())));
        importa.setIncomeDate(importRepository.findById(dto.getId()).get().getIncomeDate());
        importa.setExport(importRepository.findById(dto.getId()).get().getExport());
        importa.setNumberOfAttachments(importRepository.findById(dto.getId()).get().getNumberOfAttachments());
        importa.setCreatedBy(importRepository.findById(dto.getId()).get().getCreatedBy());
        importa.setCreatedDate(importRepository.findById(dto.getId()).get().getCreatedDate());

        importRepository.save(importa);
    }

    public void update(ImportDtoPost dto, short id) {
        dto.setId(id);
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
                ((byte) 1,
                        importa.getArchiveFile().getNum())));
        importa.setIncomeDate(importRepository.findById(dto.getId()).get().getIncomeDate());
        importa.setExport(importRepository.findById(dto.getId()).get().getExport());
        importa.setNumberOfAttachments(importRepository.findById(dto.getId()).get().getNumberOfAttachments());
        importa.setCreatedBy(importRepository.findById(dto.getId()).get().getCreatedBy());
        importa.setCreatedDate(importRepository.findById(dto.getId()).get().getCreatedDate());

        importRepository.save(importa);
    }

    public void addResponse(ExportDtoPost dto, short id) {
        Import aImport = importRepository.findById(id).get();
        if (aImport.getExport() == null) {
            exportServices.insert(dto);

            aImport.setExport(
                    new Export((short) exportServices.count())
            );
            importRepository.save(aImport);
        } else
            throw new ConflictException("This File has Response Number is " + aImport.getExport().getId());
    }


}
