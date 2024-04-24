package com.spring.services;

import com.spring.exception.ConflictException;
import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.model.dto.special.SpecialDtoPost;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.model.mapper.ImportMapper;
import com.spring.repository.ImportRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class ImportServices {

    private final ImportRepository importRepository;
    private final ArchiveFileServices archiveFileServices;
    private final ArchiveFileMapper archiveFileMapper;
    private final ImportMapper importMapper;
    private final ExportServices exportServices;
    private final SpecialServices specialServices;
    private final BaseDataServices baseDataServices;
    private final FileUploadService fileUploadService;


    @Lazy
    public ImportServices(ExportServices exportServices, ImportRepository importRepository, ArchiveFileServices archiveFileServices, ArchiveFileMapper archiveFileMapper, ImportMapper importMapper, SpecialServices specialServices, BaseDataServices baseDataServices, FileUploadService fileUploadService) {
        this.exportServices = exportServices;
        this.importRepository = importRepository;
        this.archiveFileServices = archiveFileServices;
        this.archiveFileMapper = archiveFileMapper;
        this.importMapper = importMapper;
        this.specialServices = specialServices;
        this.baseDataServices = baseDataServices;
        this.fileUploadService = fileUploadService;
    }

    /**
     * @return count imports in current year
     */
    public int count() {
        return importRepository.findByYear().size();
    }


    /**
     * @return count today imports
     */
    public int countCurrent() {
        return importRepository.findByIncomeDate().size();
    }

    /**
     * @return count imports time has come
     */
    public int countItIsTime() {
        return importRepository.findItIsTime().size();
    }

    /**
     * @return count imports time has passed
     */
    public int countPassedDate() {
        return importRepository.findPassedDate().size();
    }

    /**
     * @return count today imports
     */
    public int countImportantFile() {
        return importRepository.findImportantFile().size();
    }

    /**
     * @return count imports time has not come
     */
    public int countItIsNotTime() {
        return importRepository.findItIsNotTime().size();
    }

    /**
     * @return all imports in all years
     */
    //@Cacheable(value = "findAllImports", key = "#root.methodName")
    public List<ImportDto> findAll() {
        return importMapper.mapListToDto(importRepository.findAll());
    }

    /**
     * @return imports in current year
     */
    public List<ImportDto> findByYear() {
        return reverseList(importMapper.mapListToDto(importRepository.findByYear()));
    }

    /**
     * @param page number of page in pagination
     * @return imports in current year for pagination
     */
    public ImportDto findAllPagination(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        return importMapper.mapToDto(importRepository.findByYear(pageable).getContent().get(0));
    }

    /**
     * @param id to find import by
     * @return imports by id
     */
    public ImportDto findById(int id) {
        if (importRepository.findById(id).isPresent()) {
            Import importa = importRepository.findById(id).get();
            return importMapper.mapToDto(importa);
        } else throw new RecordNotFountException("Your search -" + id + " - did not match any documents.");
    }


    /**
     * @return today imports
     */
    public List<ImportDto> findByIncomeDate() {
        return importMapper.mapListToDto(importRepository.findByIncomeDate());
    }

    /**
     * @return imports time has come
     */
    public List<ImportDto> findItIsTime() {
        return importMapper.mapListToDto(importRepository.findItIsTime());
    }

    /**
     * @return imports time has not come
     */
    public List<ImportDto> findItIsNotTime() {
        return importMapper.mapListToDto(importRepository.findItIsNotTime());
    }

    /**
     * @return imports time has passed
     */
    public List<ImportDto> findPassedDate() {
        return importMapper.mapListToDto(importRepository.findPassedDate());
    }

    /**
     * @return important imports file
     */
    public List<ImportDto> findImportantFile() {
        return importMapper.mapListToDto(importRepository.findImportantFile());
    }

    /**
     * @param id to find archive file by id
     * @return imports in archive file
     */
    public List<ImportDto> findByArchiveFile(short id) {
        return importMapper.mapListToDto(importRepository.findByArchiveFile(id));
    }

    /**
     * @param dto add new import file
     */
    public void insert(ImportDtoPost dto) {
        List<Import> imports = importRepository.findByYear();
        dto.setTypeNumber((byte) 1);
        Import importa = importMapper.mapToEntity(dto);
        importa.setNo(imports.isEmpty() ? 1 : imports.get(imports.size() - 1).getNo() + 1);
        importa.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum(importa.getArchiveFile().getTypeNumber(), importa.getArchiveFile().getNum())));
        baseDataServices.editAutoIncrementImport();
        importRepository.save(importa);

    }

    /**
     * @param dto take new values
     * @param id  chose import file to update
     */
    public void update(ImportDtoPost dto, int id) {
        dto.setId(id);
        Import im = getById(id);
        Import importa = importMapper.mapToEntity(dto);
        dto.setTypeNumber((byte) 1);
        importa.setId(dto.getId());
        importa.setNo(im.getNo());
        importa.setSender(dto.getSender());
        importa.setIncomingLetterNumber(dto.getIncomingLetterNumber());
        importa.setIncomingLetterDate(dto.getIncomingLetterDate());
        importa.setSummary(dto.getSummary());
        importa.setIncomeDate(dto.getIncomeDate());
        importa.setRecipientName(dto.getRecipientName());
        importa.setRecipientDate(dto.getRecipientDate());
        importa.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum((byte) 1, importa.getArchiveFile().getNum())));
        importa.setExport(getById(dto.getId()).getExport());
        importa.setCreatedBy(getById(id).getCreatedBy());
        importa.setCreatedDate(getById(id).getCreatedDate());
        baseDataServices.editAutoIncrementImport();
        importRepository.save(importa);

    }

    /**
     * @param id  chose import file to add response
     * @param dto take new export file
     */
    public void addResponse(ExportDtoPost dto, int id) {
        Import aImport = getById(id);
        if (aImport.getExport() == null) {
            exportServices.insert(dto);

            aImport.setExport(
                    new Export(exportServices.findAll().get(exportServices.findAll().size() - 1).getId())
            );
            baseDataServices.editAutoIncrementImport();
            importRepository.save(aImport);

        } else
            throw new ConflictException("This File has Response Number is " + aImport.getExport().getId());
    }

    /**
     * @param year chose year
     * @return count of import in year
     */
    public int findByYearDate(String year) {
        return importRepository.findByYearDate(year).size();
    }

    private Import getById(int id) {
        return importRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }


    private List<ImportDto> reverseList(List<ImportDto> dtos) {
        Collections.reverse(dtos);
        return dtos;
    }

    public void insertAll(List<ImportDtoPost> dtos) {
        for (ImportDtoPost dto : dtos) {
            this.insert(dto);
        }
    }

    public void convertToSpecial(int id, short num) {
        Import importa = getById(id);
        SpecialDtoPost special = SpecialDtoPost.builder()
                .importNum(importa.getIncomingLetterNumber())
                .fileType("وارد")
                .summary(importa.getSummary())
                .numberOfAttachments(importa.getNumberOfAttachments())
                .num(num)
                .incomeDate(importa.getIncomeDate())
                .sender(importa.getSender())
                .build();
        specialServices.insert(special);
        fileUploadService.convertImageImport(specialServices.count(),importa.getId());
    }
}
