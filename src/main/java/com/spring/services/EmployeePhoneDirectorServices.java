package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.phonedirector.EmployeePhoneDirectorDto;
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

    public void deleteById(int id) {
        if (employeePhoneDirectorRepository.findById(id).isPresent())
            employeePhoneDirectorRepository.deleteById(id);
        else
            throw new RecordNotFountException("Record not found");
    }

}
