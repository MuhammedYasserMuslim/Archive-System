package com.spring.model.mapper;

import com.spring.model.dto.deputation.DeputationDto;
import com.spring.model.entity.Deputation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeputationMapper {

    Deputation mapToEntity(DeputationDto dto);

    DeputationDto mapToDto(Deputation dto);

    List<DeputationDto> mapToDto(List<Deputation> dtoList);

    List<Deputation> mapToEntity(List<DeputationDto> dtoList);
}
