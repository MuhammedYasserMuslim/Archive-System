package com.spring.model.dto.deputation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.model.entity.Days;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DeputationDto {


    private Integer id;


    private String degree;

    private String name;

    private String department;

    private String deputationUniversity;

    private Byte deputationPeriod;


    private List<Days> deputationDays;


    private Integer departmentRecordNum;
    private Integer departmentSpecialNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departmentDate;
    private Byte departmentAccept;


    private Integer facultyRecordNum;
    private Integer facultySpecialNum;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date facultyDate;
    private Byte facultyAccept;


    private Integer universityRecordNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date universityDate;
    private Byte universityAccept;

    private String notes;


}
