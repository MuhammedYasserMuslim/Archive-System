package com.spring.model.mapper;

import com.spring.model.dto.phonedirector.EmployeePhoneDirectorDto;
import com.spring.model.dto.phonedirector.TeachingPhoneDirectorDto;
import com.spring.model.entity.EmployeePhoneDirector;
import com.spring.model.entity.TeachingPhoneDirector;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeePhoneDirectorMapper {


    EmployeePhoneDirectorDto mapToDto(EmployeePhoneDirector employeePhoneDirector);

    List<EmployeePhoneDirectorDto> mapToDto(List<EmployeePhoneDirector> employeePhoneDirectors);

    EmployeePhoneDirector mapToEntity(EmployeePhoneDirectorDto dto);
}
