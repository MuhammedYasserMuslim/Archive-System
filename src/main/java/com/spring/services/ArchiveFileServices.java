package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.ArchiveFileDto;
import com.spring.model.entity.ArchiveFile;
import com.spring.model.enums.FileType;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.repository.ArchiveFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArchiveFileServices {

    private final ArchiveFileRepository archiveFileRepository;
    private final ArchiveFileMapper archiveFileMapper;

    public Long count() {
        return archiveFileRepository.count();
    }

    //@Cacheable(value = "findAllArchive", key = "#root.methodName")//saved in cache
    public List<ArchiveFileDto> findAll() {
        List<ArchiveFile> archiveFiles = archiveFileRepository.findAll();
        List<ArchiveFileDto> dtos = new ArrayList<>();
        for (ArchiveFile archiveFile : archiveFiles) {
            dtos.add(archiveFileMapper.mapToDto(archiveFile));
        }
        return dtos;
    }


    public ArchiveFileDto findById(Short id) {
        if (archiveFileRepository.findById(id).isPresent()) {
            ArchiveFile archiveFile = archiveFileRepository.findById(id).get();
            ArchiveFileDto archiveFileDto = archiveFileMapper.mapToDto(archiveFile);
            return archiveFileDto;
        } else throw new RecordNotFountException("This Record With id " + id + " Not Found");
    }


    public List<ArchiveFileDto> findByTypeNumber(Byte typeNumber){
        if (typeNumber==1 || typeNumber ==2 || typeNumber ==3 ) {
            List<ArchiveFile> archiveFiles = archiveFileRepository.findByTypeNumber(typeNumber);
            List<ArchiveFileDto> dtos = new ArrayList<>();
            for (int i = 0; i < archiveFiles.size(); i++) {
                dtos.add(archiveFileMapper.mapToDto(archiveFiles.get(i)));
            }
            return dtos;
        }
        else
            throw new RecordNotFountException("This Record With Type Number "+ typeNumber +" Not Found");

    }

    public List<ArchiveFileDto> findByNameContaining(String name){
        if (!archiveFileRepository.findByNameContaining(name).isEmpty() ) {
            List<ArchiveFile> archiveFiles = archiveFileRepository.findByNameContaining(name);
            List<ArchiveFileDto> dtos = new ArrayList<>();
            for (int i = 0; i < archiveFiles.size(); i++) {
                dtos.add(archiveFileMapper.mapToDto(archiveFiles.get(i)));
            }
            return dtos;

        }
        else
            throw new RecordNotFountException("This Record With name "+ name +" Not Found");

    }

















    // @CacheEvict(value = "findAllArchive", key = "#root.methodName", allEntries = true)
    //refresh cache when in method in value
    public void insert(ArchiveFileDto dto) {

        ArchiveFile archiveFile =archiveFileMapper.mapToEntity(dto);
        switch (archiveFile.getTypeNumber()) {
            case 1 -> archiveFile.setFileType(FileType.exports);
            case 2 -> archiveFile.setFileType(FileType.imports);
            case 3 -> archiveFile.setFileType(FileType.special);
            default -> throw new RuntimeException("Invalid Input Value " + archiveFile.getTypeNumber());
        }
        archiveFileRepository.save(archiveFile);
    }

    //@CacheEvict(value = "findAllArchive", key = "#root.methodName", allEntries = true)
    //refresh cache when in method in value
    public void update(ArchiveFileDto dto) {
        ArchiveFile archiveFile =archiveFileMapper.mapToEntity(dto);
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


    public void deleteById(Short id) {
        archiveFileRepository.deleteById(id);
    }
}
