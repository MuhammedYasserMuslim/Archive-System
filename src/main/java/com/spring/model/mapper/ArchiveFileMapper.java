package com.spring.model.mapper;

import com.spring.model.dto.ArchiveFileDto;
import com.spring.model.entity.ArchiveFile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArchiveFileMapper {

    ArchiveFileDto mapToDto(ArchiveFile entity);

    ArchiveFile mapToEntity(ArchiveFileDto dto);
}
