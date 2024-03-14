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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
     * @param page
     * @return exports in current year for pagination
     */
    public List<ExportDto> findAllPaginationByYear(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        List<Export> exports = exportRepository.findByYear(pageable).getContent();
        List<ExportDto> dtos = new ArrayList<>();
        for (Export export : exports) {
            dtos.add(exportMapper.mapToDto(export));
        }
        return dtos;
    }

    /**
     * @param id
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
        if (!exportRepository.findByDate().isEmpty()) {

            List<Export> exports = exportRepository.findByDate();
            List<ExportDto> dtos = new ArrayList<>();
            for (Export export : exports) {
                dtos.add(exportMapper.mapToDto(export));
            }
            return dtos;
        } else throw new RecordNotFountException("There are no new files today.");
    }

    /**
     * @param id
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
        System.out.println( exports.get(exports.size() - 1).getNo());
    }


    /**
     * @param dto,id
     * @return export file by id
     */
    // @CacheEvict(value = "findAllExports", key = "#root.methodName", allEntries = true)
    public void update(ExportDtoPost dto, int id) {
        dto.setId(id);
        Export export = exportMapper.mapToEntity(dto);
        Export ex = exportRepository.findById(id).get();
        dto.setTypeNumber((byte) 2);
        export.setId(dto.getId());
        export.setNo(ex.getNo());
        export.setReceiver(dto.getReceiver());
        export.setSummary(dto.getSummary());
        export.setDate(dto.getDate());
        export.setRecipientName(dto.getRecipientName());
        export.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum((byte) 2, export.getArchiveFile().getNum())));
        export.setAimport(exportRepository.findById(dto.getId()).get().getAimport());
        export.setCreatedBy(exportRepository.findById(dto.getId()).get().getCreatedBy());
        export.setCreatedDate(exportRepository.findById(dto.getId()).get().getCreatedDate());
        exportRepository.save(export);
    }

    /**
     * @param id,dto add urgent to export file
     */
    //@CacheEvict(value = "findAllExports", key = "#root.methodName", allEntries = true)
    public void addUrgent(ExportDtoPost dto, int id) {
        Export export = exportRepository.findById(id).get();
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
     * @param id,dto add response to export file
     */
    // @CacheEvict(value = "findAllExports", key = "#root.methodName", allEntries = true)
    public void addResponse(ImportDtoPost dto, int id) {
        Export export = exportRepository.findById(id).get();
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
     * @param year
     * @return number of exports in year
     */
    public int findByYearDate(String year) {
        return exportRepository.findByYearDate(year).size();
    }


}
