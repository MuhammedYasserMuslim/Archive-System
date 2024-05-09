package com.spring.model.mapper;

import com.spring.model.dto.phonedirector.TeachingPhoneDirectorDto;
import com.spring.model.entity.TeachingPhoneDirector;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeachingPhoneDirectorMapper {


    TeachingPhoneDirectorDto mapToDto(TeachingPhoneDirector teachingPhoneDirector);

    List<TeachingPhoneDirectorDto> mapToDto(List<TeachingPhoneDirector> teachingPhoneDirectors);

    TeachingPhoneDirector mapToEntity(TeachingPhoneDirectorDto dto);
}
