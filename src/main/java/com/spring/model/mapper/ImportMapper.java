package com.spring.model.mapper;

import com.spring.model.dto.ImportDto;
import com.spring.model.entity.Import;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {

        //ExportMapper.class,
        ArchiveFileMapper.class
})
public interface ImportMapper {

    @Mapping(target = "archiveFile",ignore = true)
    @Mapping(target = "typeNumber",source = "archiveFile.typeNumber")
    @Mapping(target = "num",source = "archiveFile.num")
    @Mapping(target = "archiveId" ,source = "archiveFile.id")
    @Mapping(target = "export",ignore = true)
    @Mapping(target = "responseNumber",source = "export.id")
    @Mapping(target = "responseDate",source = "export.date")
    @Mapping(target = "responseSide",source = "export.receiver")
    ImportDto mapToDto(Import entity);


    Import mapToEntity(ImportDto dto);


}
