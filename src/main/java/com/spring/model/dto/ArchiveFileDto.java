package com.spring.model.dto;


import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.enums.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
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

    private Set<Import> imports;

    private Set<Export> exports;

    public ArchiveFileDto(Short num, String name, Byte typeNumber) {
        this.num = num;
        this.name = name;
        this.typeNumber = typeNumber;
    }
}
