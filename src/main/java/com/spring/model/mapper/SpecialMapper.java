package com.spring.model.mapper;


import com.spring.model.dto.special.AllSpecialDto;
import com.spring.model.dto.special.SpecialDto;
import com.spring.model.dto.special.SpecialDtoPost;
import com.spring.model.entity.Special;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = ArchiveFileMapper.class)
public interface SpecialMapper {

    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(target = "typeNumber", source = "archiveFile.typeNumber")
    @Mapping(target = "num", source = "archiveFile.num")
    @Mapping(target = "archiveId", source = "archiveFile.id")
    SpecialDto mapToDto(Special entity);


    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(source = "typeNumber", target = "archiveFile.typeNumber")
    @Mapping(source = "num", target = "archiveFile.num")
    Special mapToEntity(SpecialDtoPost dto);

    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(target = "typeNumber", source = "archiveFile.typeNumber")
    @Mapping(target = "num", source = "archiveFile.num")
    @Mapping(target = "archiveId", source = "archiveFile.id")
    List<SpecialDto> mapListToDto(List<Special> specials);

    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(target = "num", source = "archiveFile.num")
    @Mapping(target = "archiveId", source = "archiveFile.id")
    AllSpecialDto mapAllToDto(Special entity);


    @Mapping(target = "archiveFile", ignore = true)
    @Mapping(target = "num", source = "archiveFile.num")
    @Mapping(target = "archiveId", source = "archiveFile.id")
    List<AllSpecialDto> mapAllToDto(List<Special> specials);
}
