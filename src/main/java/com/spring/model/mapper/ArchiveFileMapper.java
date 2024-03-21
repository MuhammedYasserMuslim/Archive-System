package com.spring.model.mapper;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.entity.ArchiveFile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArchiveFileMapper {

    ArchiveFileDto mapToDto(ArchiveFile entity);

    ArchiveFile mapToEntity(ArchiveFileDto dto);

    List<ArchiveFileDto> mapListToDto(List<ArchiveFile> archiveFiles);
}
