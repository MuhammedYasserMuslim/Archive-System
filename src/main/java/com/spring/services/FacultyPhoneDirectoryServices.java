package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.phonedirector.FacultyPhoneDirectoryDto;
import com.spring.model.dto.phonedirector.TeachingPhoneDirectorDto;
import com.spring.model.entity.FacultyPhoneDirectory;
import com.spring.model.entity.TeachingPhoneDirector;
import com.spring.model.mapper.FacultyPhoneDirectoryMapper;
import com.spring.repository.FacultyPhoneDirectoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyPhoneDirectoryServices {

    private final FacultyPhoneDirectoryRepository facultyPhoneDirectoryRepository;
    private final FacultyPhoneDirectoryMapper facultyPhoneDirectoryMapper;


    public List<FacultyPhoneDirectoryDto> findAll() {
        return facultyPhoneDirectoryMapper.mapToDto(facultyPhoneDirectoryRepository.findAllByOrderBySerialAsc());
    }

    public void insert(FacultyPhoneDirectoryDto dto) {
        facultyPhoneDirectoryRepository.save(facultyPhoneDirectoryMapper.mapToEntity(dto));
    }

    public FacultyPhoneDirectoryDto findById(Integer id) {
        FacultyPhoneDirectory entity = facultyPhoneDirectoryRepository.findById(id).orElseThrow(() -> new RecordNotFountException(""));
        return facultyPhoneDirectoryMapper.mapToDto(entity);
    }

    public void deleteById(int id) {
        if (facultyPhoneDirectoryRepository.findById(id).isPresent())
            facultyPhoneDirectoryRepository.deleteById(id);
        else
            throw new RecordNotFountException("Record not found");
    }

    public void update(FacultyPhoneDirectoryDto dto, int id) {
        deleteById(id);
        facultyPhoneDirectoryRepository.save(facultyPhoneDirectoryMapper.mapToEntity(dto));
    }
}
