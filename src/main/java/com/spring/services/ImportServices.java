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
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class ImportServices {

    private final ImportRepository importRepository;
    private final ArchiveFileServices archiveFileServices;
    private final ArchiveFileMapper archiveFileMapper;
    private final ImportMapper importMapper;
    private final ExportServices exportServices;



    @Lazy
    public ImportServices(ExportServices exportServices, ImportRepository importRepository, ArchiveFileServices archiveFileServices, ArchiveFileMapper archiveFileMapper, ImportMapper importMapper) {
        this.exportServices = exportServices;
        this.importRepository = importRepository;
        this.archiveFileServices = archiveFileServices;
        this.archiveFileMapper = archiveFileMapper;
        this.importMapper = importMapper;
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
        List<Import> imports = importRepository.findAll();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports)
            dtos.add(importMapper.mapToDto(anImport));
        return dtos;
    }

    /**
     * @return imports in current year
     */
    public List<ImportDto> findByYear() {
        List<Import> imports = importRepository.findByYear();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports)
            dtos.add(importMapper.mapToDto(anImport));
        Collections.reverse(dtos);
        return dtos;
    }

    /**
     * @param page number of page in pagination
     * @return imports in current year for pagination
     */
    public List<ImportDto> findAllPagination(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        List<Import> imports = importRepository.findByYear(pageable).getContent();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import importa : imports)
            dtos.add(importMapper.mapToDto(importa));
        return dtos;
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
        List<Import> imports = importRepository.findByIncomeDate();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports)
            dtos.add(importMapper.mapToDto(anImport));
        return dtos;
    }

    /**
     * @return imports time has come
     */
    public List<ImportDto> findItIsTime() {
        List<Import> imports = importRepository.findItIsTime();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports)
            dtos.add(importMapper.mapToDto(anImport));
        return dtos;
    }

    /**
     * @return imports time has not come
     */
    public List<ImportDto> findItIsNotTime() {
        List<Import> imports = importRepository.findItIsNotTime();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports)
            dtos.add(importMapper.mapToDto(anImport));
        return dtos;
    }

    /**
     * @return imports time has passed
     */
    public List<ImportDto> findPassedDate() {
        List<Import> imports = importRepository.findPassedDate();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports)
            dtos.add(importMapper.mapToDto(anImport));
        return dtos;
    }

    /**
     * @return important imports file
     */
    public List<ImportDto> findImportantFile() {
        List<Import> imports = importRepository.findImportantFile();
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports)
            dtos.add(importMapper.mapToDto(anImport));
        return dtos;
    }

    /**
     * @param id to find archive file by id
     * @return imports in archive file
     */
    public List<ImportDto> findByArchiveFile(short id) {
        ArchiveFileDto dto = archiveFileServices.findById(id);
        List<Import> imports = importRepository.findByArchiveFile(archiveFileMapper.mapToEntity(dto));
        List<ImportDto> dtos = new ArrayList<>();
        for (Import anImport : imports)
            dtos.add(importMapper.mapToDto(anImport));
        return dtos;
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
                    new Export(exportServices.count())
            );
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
}
