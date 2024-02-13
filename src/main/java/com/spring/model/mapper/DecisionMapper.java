package com.spring.model.mapper;


import com.spring.model.dto.special.DecisionDto;
import com.spring.model.entity.Decision;
import org.mapstruct.Mapper;

@Mapper
public interface DecisionMapper {

    DecisionDto mapToDto(Decision entity);

    Decision mapToEntity(DecisionDto dto);
}
