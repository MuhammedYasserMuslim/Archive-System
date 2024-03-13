package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.entity.ArchiveFile;
import com.spring.model.enums.FileType;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.repository.ArchiveFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchiveFileServices {

    private final ArchiveFileRepository archiveFileRepository;
    private final ArchiveFileMapper archiveFileMapper;

    /**
     * @return count  archiveFile
     */
    public Long count() {
        return archiveFileRepository.count();
    }

    /**
     * @return all archiveFiles
     */

    //@Cacheable(value = "findAllArchive", key = "#root.methodName")//saved in cache
    public List<ArchiveFileDto> findAll() {
        List<ArchiveFile> archiveFiles = archiveFileRepository.findAll();
        List<ArchiveFileDto> dtos = new ArrayList<>();
        for (ArchiveFile archiveFile : archiveFiles) {
            dtos.add(archiveFileMapper.mapToDto(archiveFile));
        }
        return dtos;
    }

    /**
     * @param id
     * @return archiveFile by id
     */
    public ArchiveFileDto findById(short id) {
        if (archiveFileRepository.findById(id).isPresent()) {
            ArchiveFile archiveFile = archiveFileRepository.findById(id).get();
            return archiveFileMapper.mapToDto(archiveFile);
        } else throw new RecordNotFountException("This Record With id " + id + " Not Found");
    }

    /**
     * @param typeNumber
     * @return archiveFile by TypeNumber
     */
    public List<ArchiveFileDto> findByTypeNumber(Byte typeNumber) {
        if (typeNumber == 1 || typeNumber == 2 || typeNumber == 3) {
            List<ArchiveFile> archiveFiles = archiveFileRepository.findByTypeNumber(typeNumber);
            List<ArchiveFileDto> dtos = new ArrayList<>();
            for (ArchiveFile archiveFile : archiveFiles) {
                dtos.add(archiveFileMapper.mapToDto(archiveFile));
            }
            return dtos;
        } else
            throw new RecordNotFountException("This Record With Type Number " + typeNumber + " Not Found");

    }

    /**
     * @param name
     * @return archiveFile by name
     */
    public List<ArchiveFileDto> findByNameContaining(String name) {
        if (!archiveFileRepository.findByNameContaining(name).isEmpty()) {
            List<ArchiveFile> archiveFiles = archiveFileRepository.findByNameContaining(name);
            List<ArchiveFileDto> dtos = new ArrayList<>();
            for (ArchiveFile archiveFile : archiveFiles) {
                dtos.add(archiveFileMapper.mapToDto(archiveFile));
            }
            return dtos;

        } else
            throw new RecordNotFountException("This Record With name " + name + " Not Found");

    }

    /**
     * @param typeNumber,num
     * @return archiveFile by typeNumber and num
     */
    public ArchiveFileDto findByTypeNumberAndNum(Byte typeNumber, Short num) {
        if (archiveFileRepository.findByTypeNumberAndNum(typeNumber, num).isPresent()) {
            ArchiveFile archiveFile = archiveFileRepository.findByTypeNumberAndNum(typeNumber, num).get();
            return archiveFileMapper.mapToDto(archiveFile);
        } else throw new RecordNotFountException("This Record With number " + typeNumber + "/" + num + " Not Found");
    }


    /**
     * @param dto add new archiveFile
     */
    // @CacheEvict(value = "findAllArchive", key = "#root.methodName", allEntries = true)
    //refresh cache when in method in value
    public void insert(ArchiveFileDto dto) {
        ArchiveFile archiveFile = archiveFileMapper.mapToEntity(dto);
        switch (archiveFile.getTypeNumber()) {
            case 1 -> archiveFile.setFileType(FileType.imports);
            case 2 -> archiveFile.setFileType(FileType.exports);
            case 3 -> archiveFile.setFileType(FileType.special);
            default -> throw new RuntimeException("Invalid Input Value " + archiveFile.getTypeNumber());
        }
        archiveFileRepository.save(archiveFile);
    }

    /**
     * @param dto update archiveFile file by id
     */
    //@CacheEvict(value = "findAllArchive", key = "#root.methodName", allEntries = true)
    //refresh cache when in method in value
    public void update(ArchiveFileDto dto) {
        ArchiveFile archiveFile = archiveFileMapper.mapToEntity(dto);
        archiveFileRepository.save(archiveFile);
    }


    public void saveAll(List<ArchiveFileDto> dtos) {
        List<ArchiveFile> archiveFiles = new ArrayList<>();
        for (ArchiveFileDto dto : dtos) {
            ArchiveFile archiveFile = archiveFileMapper.mapToEntity(dto);
            switch (archiveFile.getTypeNumber()) {
                case 1 -> archiveFile.setFileType(FileType.exports);
                case 2 -> archiveFile.setFileType(FileType.imports);
                case 3 -> archiveFile.setFileType(FileType.special);
                default -> throw new RuntimeException("Invalid Input Value " + archiveFile.getTypeNumber());
            }
            archiveFiles.add(archiveFile);
        }
        archiveFileRepository.saveAll(archiveFiles);
    }

}
