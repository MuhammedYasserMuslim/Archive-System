package com.spring.model.mapper;


import com.spring.model.dto.special.SubjectDto;
import com.spring.model.entity.Subject;
import org.mapstruct.Mapper;

@Mapper
public interface SubjectMapper {

    SubjectDto mapToDto(Subject entity);

    Subject mapToEntity(SubjectDto dto);


}
