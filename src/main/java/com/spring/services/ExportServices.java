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
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


@Service
public class ExportServices {

    private final ExportRepository exportRepository;
    private final ArchiveFileServices archiveFileServices;
    private final ArchiveFileMapper archiveFileMapper;
    private final ExportMapper exportMapper;
    private final ImportServices importServices;


    @Lazy
    public ExportServices(ImportServices importServices, ExportMapper exportMapper, ArchiveFileMapper archiveFileMapper, ArchiveFileServices archiveFileServices, ExportRepository exportRepository) {
        this.importServices = importServices;
        this.exportMapper = exportMapper;
        this.archiveFileMapper = archiveFileMapper;
        this.archiveFileServices = archiveFileServices;
        this.exportRepository = exportRepository;
    }

    /**
     * @return count exports in current year
     */
    public int count() {
        return exportRepository.findByYear().size();
    }

    /**
     * @return count today  exports
     */
    public int countCurrent() {
        return exportRepository.findByDate().size();
    }

    /**
     * @return all exports in all years
     */
    //  @Cacheable(value = "findAllExports", key = "#root.methodName")
    public List<ExportDto> findAll() {
        List<Export> exports = exportRepository.findAll();
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }
        return dtos;
    }

    /**
     * @return exports in current year
     */
    public List<ExportDto> findByYear() {
        List<Export> exports = exportRepository.findByYear();
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports)
            dtos.add(exportMapper.mapToDto(export));
        Collections.reverse(dtos);
        return dtos;
    }

    /**
     * @param page number of page in pagination
     * @return exports in current year for pagination
     */
    public ExportDto findAllPaginationByYear(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        List<Export> exports = exportRepository.findByYear(pageable).getContent();
        return exportMapper.mapToDto(exports.get(0));
    }

    /**
     * @param id to find export by
     * @return exports by id
     */
    //@Cacheable(value = "findAllExports", key = "#root.methodName")
    public ExportDto findById(int id) {

        if (exportRepository.findById(id).isPresent()) {
            Export export = exportRepository.findById(id).get();
            return exportMapper.mapToDto(export);
        } else
            throw new RecordNotFountException("Your search -" + id + " - did not match any documents.");


    }

    /**
     * @return today exports
     */
    public List<ExportDto> findByDate() {

        List<Export> exports = exportRepository.findByDate();
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }
        return dtos;

    }

    /**
     * @param id to find archive file by id
     * @return exports in archive file
     */
    public List<ExportDto> findByArchiveFile(short id) {
        ArchiveFileDto dto = archiveFileServices.findById(id);
        List<Export> exports = exportRepository.findByArchiveFile(archiveFileMapper.mapToEntity(dto));
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }

        return dtos;
    }

    /**
     * @param dto add new export file
     */
    // @CacheEvict(value = "findAllExports", key = "#root.methodName", allEntries = true)
    public void insert(ExportDtoPost dto) {
        dto.setTypeNumber((byte) 2);
        Export export = exportMapper.mapToEntity(dto);
        List<Export> exports = exportRepository.findByYear();
        export.setNo(exports.isEmpty() ? 1 : exports.get(exports.size() - 1).getNo() + 1);
        export.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
                (export.getArchiveFile().getTypeNumber(),
                        export.getArchiveFile().getNum())));
        exportRepository.save(export);
        System.out.println(exports.get(exports.size() - 1).getNo());
    }


    /**
     * @param dto take new values
     * @param id  chose export file to update
     */
    // @CacheEvict(value = "findAllExports", key = "#root.methodName", allEntries = true)
    public void update(ExportDtoPost dto, int id) {
        dto.setId(id);
        Export export = exportMapper.mapToEntity(dto);
        Export ex = getById(id);
        dto.setTypeNumber((byte) 2);
        export.setId(dto.getId());
        export.setNo(ex.getNo());
        export.setReceiver(dto.getReceiver());
        export.setSummary(dto.getSummary());
        export.setDate(dto.getDate());
        export.setRecipientName(dto.getRecipientName());
        export.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum((byte) 2, export.getArchiveFile().getNum())));
        export.setAimport(getById(dto.getId()).getAimport());
        export.setCreatedBy(getById(dto.getId()).getCreatedBy());
        export.setCreatedDate(getById(dto.getId()).getCreatedDate());
        exportRepository.save(export);
    }

    /**
     * @param id  chose export file to add urgent
     * @param dto take new export file
     */
    //@CacheEvict(value = "findAllExports", key = "#root.methodName", allEntries = true)
    public void addUrgent(ExportDtoPost dto, int id) {
        Export export =getById(id);
        if (export.getUrgentNum() == null) {
            List<Export> exports = exportRepository.findAll();
            insert(dto);
            export.setUrgentNum(exports.get(exports.size() - 1).getNo() + 1);
            export.setUrgentDate(new Date());
            exportRepository.save(export);
        } else
            throw new ConflictException("This File has Urgent Number is " + export.getUrgentNum());
    }

    /**
     * @param id  chose export file to add response
     * @param dto take new import file
     */
    // @CacheEvict(value = "findAllExports", key = "#root.methodName", allEntries = true)
    public void addResponse(ImportDtoPost dto, int id) {
        Export export = getById(id);
        if (export.getAimport() == null) {
            importServices.insert(dto);
            export.setAimport(
                    new Import(importServices.count())
            );
            exportRepository.save(export);
        } else
            throw new ConflictException("This File has Response Number is " + export.getAimport().getId());
    }


    /**
     * @param year chose year
     * @return count of exports in year
     */
    public int findByYearDate(String year) {
        return exportRepository.findByYearDate(year).size();
    }

    private Export getById(int id) {
        return exportRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }
}
