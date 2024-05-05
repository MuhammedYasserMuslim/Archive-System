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

    public List<ArchiveFileDto> findAll() {
        return archiveFileMapper.mapListToDto(archiveFileRepository.findAll());
    }

    /**
     * @param id find archive file by
     * @return archiveFile by id
     */
    public ArchiveFileDto findById(short id) {
        if (archiveFileRepository.findById(id).isPresent()) {
            ArchiveFile archiveFile = archiveFileRepository.findById(id).get();
            return archiveFileMapper.mapToDto(archiveFile);
        } else throw new RecordNotFountException("This Record With id " + id + " Not Found");
    }


    /**
     * @param name find archive g=file by
     * @return archiveFile by name
     */
    public List<ArchiveFileDto> findByNameContaining(String name) {
        if (!archiveFileRepository.findByNameContaining(name).isEmpty())
            return archiveFileMapper.mapListToDto(archiveFileRepository.findByNameContaining(name));
        else
            throw new RecordNotFountException("This Record With name " + name + " Not Found");

    }

    /**
     * @param typeNumber,num find archive file by
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
    public void update(ArchiveFileDto dto) {
        ArchiveFile archiveFile = archiveFileMapper.mapToEntity(dto);
        archiveFileRepository.save(archiveFile);
    }

}
