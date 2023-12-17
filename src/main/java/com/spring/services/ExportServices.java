package com.spring.services;

import com.spring.exception.ConflictException;
import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.exports.ExportDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.model.mapper.ExportMapper;
import com.spring.repository.ExportRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExportServices {

    @Autowired
    private ExportRepository exportRepository;
    @Autowired
    private ArchiveFileServices archiveFileServices;
    @Autowired
    private ArchiveFileMapper archiveFileMapper;
    @Autowired
    private ExportMapper exportMapper;
    private final ImportServices importServices;

    @Lazy
    public ExportServices(ImportServices importServices) {
        this.importServices = importServices;
    }

    public long count() {
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

    //@Cacheable(value = "findAllExports", key = "#root.methodName")
    public ExportDto findById(Short id) {

        if (exportRepository.findById(id).isPresent()) {
            Export export = exportRepository.findById(id).get();
            return exportMapper.mapToDto(export);
        } else
            throw new RecordNotFountException("Your search -" + id + " - did not match any documents.");


    }

    public List<ExportDto> findBySummary(String summary) {
        if (!exportRepository.findBySummaryContaining(summary).isEmpty()) {
            List<Export> exports = exportRepository.findBySummaryContaining(summary);
            List<ExportDto> dtos = new ArrayList<>();
            for (Export export : exports) {
                dtos.add(exportMapper.mapToDto(export));
            }
            return dtos;
        } else throw new RecordNotFountException("Your search -" + summary + " - did not match any documents.");
    }

    public List<ExportDto> findByDate() {
        if (!exportRepository.findByDate().isEmpty()) {

            List<Export> exports = exportRepository.findByDate();
            List<ExportDto> dtos = new ArrayList<>();
            for (Export export : exports) {
                dtos.add(exportMapper.mapToDto(export));
            }
            return dtos;
        } else throw new RecordNotFountException("There are no new files today.");
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

    public void insert(ExportDtoPost dto) {
        dto.setTypeNumber((byte) 2);
        Export export = exportMapper.mapToEntity(dto);
        export.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
                (export.getArchiveFile().getTypeNumber(),
                        export.getArchiveFile().getNum())));
        exportRepository.save(export);
    }

    public void update(ExportDtoPost dto) {
        Export export = exportMapper.mapToEntity(dto);
        dto.setTypeNumber((byte) 2);
        export.setId(dto.getId());
        export.setReceiver(dto.getReceiver());
        export.setSummary(dto.getSummary());
        export.setRecipientName(dto.getRecipientName());
        export.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
                ((byte) 2,
                        export.getArchiveFile().getNum())));
        export.setDate(exportRepository.findById(dto.getId()).get().getDate());
        export.setAimport(exportRepository.findById(dto.getId()).get().getAimport());
        export.setNumberOfAttachments(exportRepository.findById(dto.getId()).get().getNumberOfAttachments());
        export.setCreatedBy(exportRepository.findById(dto.getId()).get().getCreatedBy());
        export.setCreatedDate(exportRepository.findById(dto.getId()).get().getCreatedDate());

        exportRepository.save(export);
    }

    public void update(ExportDtoPost dto, Short id) {
        Export export = exportMapper.mapToEntity(dto);
        dto.setTypeNumber((byte) 2);
        export.setId(id);
        export.setReceiver(dto.getReceiver());
        export.setSummary(dto.getSummary());
        export.setRecipientName(dto.getRecipientName());
        export.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
                ((byte) 2,
                        export.getArchiveFile().getNum())));
        export.setDate(exportRepository.findById(dto.getId()).get().getDate());
        export.setAimport(exportRepository.findById(dto.getId()).get().getAimport());
        export.setNumberOfAttachments(exportRepository.findById(dto.getId()).get().getNumberOfAttachments());
        export.setCreatedBy(exportRepository.findById(dto.getId()).get().getCreatedBy());
        export.setCreatedDate(exportRepository.findById(dto.getId()).get().getCreatedDate());

        exportRepository.save(export);
    }

    public void addUrgent(ExportDtoPost dto, short id) {
        Export export = exportRepository.findById(id).get();
        if (export.getUrgentNum() == null) {
            insert(dto);
            export.setUrgentNum((short) exportRepository.count());
            export.setUrgentDate(new Date());
            exportRepository.save(export);
        } else
            throw new ConflictException("This File has Urgent Number is " + export.getUrgentNum());
    }

    public void addResponse(ImportDtoPost dto, short id) {
        Export export = exportRepository.findById(id).get();
        if (export.getAimport() == null) {
            importServices.insert(dto);

            export.setAimport(
                    new Import((short) importServices.count())
            );
            exportRepository.save(export);
        } else
            throw new ConflictException("This File has Response Number is " + export.getAimport().getId());
    }

}
