package com.spring.model.mapper;

import com.spring.model.dto.ExportDto;
import com.spring.model.entity.Export;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ArchiveFileMapper.class)
public interface ExportMapper {

    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(target = "typeNumber", source = "archiveFile.typeNumber")
    @Mapping(target = "num", source = "archiveFile.num")
    @Mapping(target = "archiveId", source = "archiveFile.id")
    @Mapping(target = "aimport",ignore = true)
    @Mapping(target = "responseNumber", source = "aimport.id")
    @Mapping(target = "responseDate", source = "aimport.incomeDate")
    @Mapping(target = "importRel",ignore = true)
    ExportDto mapToDto(Export entity);



    @Mapping(source = "archiveId" ,target = "archiveFile.id")
    @Mapping(source = "responseNumber", target = "aimport.id")
    @Mapping(target = "aimport",ignore = true)
    @Mapping(target = "archiveFile",ignore = true)
    Export mapToEntity(ExportDto dto);


}
