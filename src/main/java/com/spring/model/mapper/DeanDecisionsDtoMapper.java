package com.spring.model.mapper;

import com.spring.model.dto.deandecisions.DeanDecisionsDto;
import com.spring.model.dto.deandecisions.DeanDecisionsDtoPost;
import com.spring.model.entity.DeanDecisions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ArchiveFileMapper.class)
public interface DeanDecisionsDtoMapper {

    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(target = "num", source = "archiveFile.num")
    @Mapping(target = "archiveId", source = "archiveFile.id")
    DeanDecisionsDto mapToDto(DeanDecisions entity);

    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(source = "typeNumber", target = "archiveFile.typeNumber")
    @Mapping(source = "num", target = "archiveFile.num")
    DeanDecisions mapToEntity(DeanDecisionsDtoPost dto);

    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(target = "num", source = "archiveFile.num")
    @Mapping(target = "archiveId", source = "archiveFile.id")
    List<DeanDecisionsDto> mapListToDto(List<DeanDecisions> exports);




}
