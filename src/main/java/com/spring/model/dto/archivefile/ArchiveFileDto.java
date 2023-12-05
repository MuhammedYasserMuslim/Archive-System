package com.spring.model.dto.archivefile;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveFileDto {

    private Short id;
    private Short num;
    private String name;
    private Byte typeNumber;
    @JsonIgnore
    private Set<Import> imports;
    @JsonIgnore
    private Set<Export> exports;

    public ArchiveFileDto(Short num, String name, Byte typeNumber) {
        this.num = num;
        this.name = name;
        this.typeNumber = typeNumber;
    }
}
