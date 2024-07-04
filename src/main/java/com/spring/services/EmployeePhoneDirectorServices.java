package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.phonedirector.EmployeePhoneDirectorDto;
import com.spring.model.dto.phonedirector.FacultyPhoneDirectoryDto;
import com.spring.model.entity.EmployeePhoneDirector;
import com.spring.model.entity.FacultyPhoneDirectory;
import com.spring.model.mapper.EmployeePhoneDirectorMapper;
import com.spring.repository.EmployeePhoneDirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeePhoneDirectorServices {

    private final EmployeePhoneDirectorRepository employeePhoneDirectorRepository;
    private final EmployeePhoneDirectorMapper employeePhoneDirectorMapper;


    public List<EmployeePhoneDirectorDto> findAll() {
        return employeePhoneDirectorMapper.mapToDto(employeePhoneDirectorRepository.findAllByOrderBySerialAsc());
    }

    public void insert(EmployeePhoneDirectorDto dto) {
        employeePhoneDirectorRepository.save(employeePhoneDirectorMapper.mapToEntity(dto));
    }

    public EmployeePhoneDirectorDto findById(Integer id) {
        EmployeePhoneDirector entity = employeePhoneDirectorRepository.findById(id).orElseThrow(() -> new RecordNotFountException(""));
        return employeePhoneDirectorMapper.mapToDto(entity);
    }

    public void deleteById(int id) {
        if (employeePhoneDirectorRepository.findById(id).isPresent())
            employeePhoneDirectorRepository.deleteById(id);
        else
            throw new RecordNotFountException("Record not found");
    }

    public void update(EmployeePhoneDirectorDto teachingPhoneDirectorDto, int id) {
        teachingPhoneDirectorDto.setId(id);
        employeePhoneDirectorRepository.save(employeePhoneDirectorMapper.mapToEntity(teachingPhoneDirectorDto));
    }
}
