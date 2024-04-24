package com.spring.services;

import com.spring.exception.ConflictException;
import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.exports.ExportDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.model.dto.special.SpecialDtoPost;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.model.mapper.ExportMapper;
import com.spring.repository.ExportRepository;
import lombok.Builder;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class ExportServices {

    private final ExportRepository exportRepository;
    private final ArchiveFileServices archiveFileServices;
    private final ArchiveFileMapper archiveFileMapper;
    private final ExportMapper exportMapper;
    private final ImportServices importServices;
    private final SpecialServices specialServices;
    private final BaseDataServices baseDataServices;


    @Lazy
    public ExportServices(ImportServices importServices, ExportMapper exportMapper, ArchiveFileMapper archiveFileMapper, ArchiveFileServices archiveFileServices, ExportRepository exportRepository, SpecialServices specialServices, BaseDataServices baseDataServices) {
        this.importServices = importServices;
        this.exportMapper = exportMapper;
        this.archiveFileMapper = archiveFileMapper;
        this.archiveFileServices = archiveFileServices;
        this.exportRepository = exportRepository;
        this.specialServices = specialServices;
        this.baseDataServices = baseDataServices;
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
        return exportMapper.mapListToDto(exportRepository.findAll());
    }

    /**
     * @return exports in current year
     */
    public List<ExportDto> findByYear() {
        return reverseList(exportMapper.mapListToDto(exportRepository.findByYear()));
    }

    public Export findByNo(int no) {
        if (no <= findByYear().size())
            return exportRepository.findByYear().get(no - 1);
        else
            throw new RecordNotFountException("This record with no :" + no + " Not Found");
    }

    /**
     * @param page number of page in pagination
     * @return exports in current year for pagination
     */
    public ExportDto findAllPaginationByYear(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        return exportMapper.mapToDto(exportRepository.findByYear(pageable).getContent().get(0));
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
        return exportMapper.mapListToDto(exportRepository.findByDate());
    }

    /**
     * @param id to find archive file by id
     * @return exports in archive file
     */
    public List<ExportDto> findByArchiveFile(short id) {
        return exportMapper.mapListToDto(exportRepository.findByArchiveFile(id));
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
        baseDataServices.editAutoIncrementExport();
        exportRepository.save(export);

    }

    public void insertAll(List<ExportDtoPost> dtos) {
        for (ExportDtoPost dto : dtos) {
            this.insert(dto);
        }

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
        baseDataServices.editAutoIncrementExport();
        exportRepository.save(export);

    }

    /**
     * @param id  chose export file to add urgent
     * @param dto take new export file
     */
    //@CacheEvict(value = "findAllExports", key = "#root.methodName", allEntries = true)
    public void addUrgent(ExportDtoPost dto, int id) {
        Export export = findByNo(id);
        if (export.getUrgentNum() == null) {
            List<Export> exports = exportRepository.findByYear();
            insert(dto);
            export.setUrgentNum(exports.get(exports.size() - 1).getNo() + 1);
            export.setUrgentDate(dto.getDate());
            baseDataServices.editAutoIncrementExport();
            exportRepository.save(export);

        } else
            throw new ConflictException("This File has Urgent Number : " + export.getUrgentNum());
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
                    new Import(importServices.findAll().get(importServices.findAll().size() - 1).getId())
            );
            baseDataServices.editAutoIncrementExport();
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

    private List<ExportDto> reverseList(List<ExportDto> dtos) {
        Collections.reverse(dtos);
        return dtos;
    }


//    private void changeArchiveFile( Export export , short num) {
//        export.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum((byte) 3, num)));
//        exportRepository.save(export);
//    }

    public void convertToSpecial(int id , short num){
        Export export = getById(id);
        SpecialDtoPost special = SpecialDtoPost.builder()
                .importNum(export.getNo())
                .summary(export.getSummary())
                .numberOfAttachments(export.getNumberOfAttachments())
                .num(num)
                .incomeDate(export.getDate())
                .sender(export.getReceiver())
                .build();
        specialServices.insert(special);
    }


}
