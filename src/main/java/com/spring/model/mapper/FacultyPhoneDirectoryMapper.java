package com.spring.model.mapper;

import com.spring.model.dto.phonedirector.FacultyPhoneDirectoryDto;
import com.spring.model.entity.FacultyPhoneDirectory;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacultyPhoneDirectoryMapper {

    FacultyPhoneDirectoryDto mapToDto(FacultyPhoneDirectory facultyPhoneDirectory);

    List<FacultyPhoneDirectoryDto> mapToDto(List<FacultyPhoneDirectory> facultyPhoneDirectories);

    FacultyPhoneDirectory mapToEntity(FacultyPhoneDirectoryDto facultyPhoneDirectoryDto);
}
