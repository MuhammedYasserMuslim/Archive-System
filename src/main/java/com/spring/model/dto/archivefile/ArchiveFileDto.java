package com.spring.model.dto.archivefile;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.entity.Special;
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

    @JsonIgnore
    private Set<Special> specials;

    private boolean canDelete;

    private Integer count;

    public ArchiveFileDto(Short num, String name, Byte typeNumber) {
        this.num = num;
        this.name = name;
        this.typeNumber = typeNumber;
    }

    public boolean isCanDelete() {
        return imports.isEmpty() && exports.isEmpty() && specials.isEmpty();
    }

    public Integer getCount() {
        return imports.stream().filter(anImport -> anImport.getSaved() == 0).toList().size()
                + exports.stream().filter(export -> export.getSaved() == 0).toList().size()
                + specials.size();
    }
}
