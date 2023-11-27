package com.spring.model.dto;


import com.spring.model.enums.FileType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ArchiveFileDto {

    private Short id;

    private Short num;

    private String name;

    private Byte typeNumber;

    public ArchiveFileDto(Short num, String name, Byte typeNumber) {
        this.num = num;
        this.name = name;
        this.typeNumber = typeNumber;
    }
}
