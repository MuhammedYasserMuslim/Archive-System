package com.spring.model.mapper;

import com.spring.model.dto.exports.ExportDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.exports.ExportDtoPut;
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
    ExportDto mapToDto(Export entity);



    @Mapping(source = "archiveId" ,target = "archiveFile.id")
    @Mapping(target = "archiveFile",ignore = true)
    @Mapping(source = "typeNumber",target = "archiveFile.typeNumber")
    @Mapping(source = "num",target = "archiveFile.num")
    Export mapToEntity(ExportDtoPost dto);



    @Mapping(source = "archiveId" ,target = "archiveFile.id")
    @Mapping(target = "archiveFile",ignore = true)
    @Mapping(source = "typeNumber",target = "archiveFile.typeNumber")
    @Mapping(source = "num",target = "archiveFile.num")
    Export maoToEntityPut(ExportDtoPut dto);


}
