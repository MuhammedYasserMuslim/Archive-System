package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.phonedirector.TeachingPhoneDirectorDto;
import com.spring.model.entity.TeachingPhoneDirector;
import com.spring.model.mapper.TeachingPhoneDirectorMapper;
import com.spring.repository.TeachingPhoneDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeachingPhoneDirectorServices {

    private final TeachingPhoneDirectorRepository teachingPhoneDirectorRepository;
    private final TeachingPhoneDirectorMapper teachingPhoneDirectorMapper;


    public List<TeachingPhoneDirectorDto> findAll() {
        return teachingPhoneDirectorMapper.mapToDto(teachingPhoneDirectorRepository.findAllByOrderBySerialAsc());
    }

    public void insert(TeachingPhoneDirectorDto dto) {
        teachingPhoneDirectorRepository.save(teachingPhoneDirectorMapper.mapToEntity(dto));
    }

    public TeachingPhoneDirectorDto findById(Integer id) {
        TeachingPhoneDirector entity = teachingPhoneDirectorRepository.findById(id).orElseThrow(() -> new RecordNotFountException(""));
        return teachingPhoneDirectorMapper.mapToDto(entity);
    }

    public void deleteById(int id) {
        if (teachingPhoneDirectorRepository.findById(id).isPresent())
            teachingPhoneDirectorRepository.deleteById(id);
        else
            throw new RecordNotFountException("Record not found");
    }

    public void update(TeachingPhoneDirectorDto teachingPhoneDirectorDto, int id) {
        teachingPhoneDirectorDto.setId(id);
        teachingPhoneDirectorRepository.save(teachingPhoneDirectorMapper.mapToEntity(teachingPhoneDirectorDto));
    }
}
