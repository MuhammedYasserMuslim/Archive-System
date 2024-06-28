package com.spring.model.dto.archivefile;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.entity.DeanDecisions;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import com.spring.model.entity.Special;
import com.spring.services.ArchiveFileServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
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

    @JsonIgnore
    private Set<DeanDecisions> deanDecisions;

    private boolean canDelete;


    private Integer count;

    public ArchiveFileDto(Short num, String name, Byte typeNumber) {
        this.num = num;
        this.name = name;
        this.typeNumber = typeNumber;
    }

    public boolean isCanDelete() {
        return imports.isEmpty() && exports.isEmpty() && specials.isEmpty()&& deanDecisions.isEmpty();
    }

    public Integer getCount() {
        return imports.stream().filter(anImport -> anImport.getSaved() == 0 && checkMonth(anImport.getCreatedDate())[0] == checkMonth(LocalDateTime.now())[0]).toList().size() +
               exports.stream().filter(export -> export.getSaved() == 0 && checkMonth(export.getCreatedDate())[0] == checkMonth(LocalDateTime.now())[0]).toList().size() +
               specials.stream().filter(special -> checkMonth(special.getCreatedDate())[0] == checkMonth(LocalDateTime.now())[0]).toList().size()+
                deanDecisions.stream().filter(decisions -> checkMonth(decisions.getCreatedDate())[0] == checkMonth(LocalDateTime.now())[0]).toList().size();
    }


    private static int[] checkMonth(LocalDateTime dateTime) {
        int month = dateTime.getMonthValue();
        int currentYear = dateTime.getYear();
        int[] years = new int[2];
        if (month >= 7) {
            years[0] = currentYear;
            years[1] = currentYear + 1;
        } else {
            years[0] = currentYear - 1;
            years[1] = currentYear;
        }
        return years;
    }


}
