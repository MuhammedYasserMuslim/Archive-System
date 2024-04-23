package com.spring.model.mapper;

import com.spring.model.dto.incomesigns.IncomingSignsDto;
import com.spring.model.entity.IncomingSigns;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IncomingSignsMapper {

    IncomingSignsDto mapToDto(IncomingSigns incomingSigns);

    List<IncomingSignsDto> mapToDto(List<IncomingSigns> incomingSigns);

    IncomingSigns mapToEntity(IncomingSignsDto incomingSigns);


}
