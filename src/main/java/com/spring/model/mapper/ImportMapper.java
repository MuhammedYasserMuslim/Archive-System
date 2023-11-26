package com.spring.model.mapper;

import com.spring.model.dto.ImportDto;
import com.spring.model.entity.Import;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {

        ExportMapper.class,
        ArchiveFileMapper.class
})
public interface ImportMapper {

    @Mapping(target = "archiveFile",ignore = true)
    @Mapping(target = "typeNumber",source = "archiveFile.typeNumber")
    @Mapping(target = "num",source = "archiveFile.num")
    @Mapping(target = "export",ignore = true)
    @Mapping(target = "responseNumber",source = "export.id")
    @Mapping(target = "responseDate",source = "export.date")
    @Mapping(target = "responseSide",source = "export.receiver")
    ImportDto mapToDto(Import entity);


    @Mapping(source = "typeNumber",target = "archiveFile.typeNumber")
    @Mapping(source = "num",target = "archiveFile.num")
    Import mapToEntity(ImportDto dto);


}
