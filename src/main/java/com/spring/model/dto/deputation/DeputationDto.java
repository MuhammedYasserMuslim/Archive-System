package com.spring.model.dto.deputation;

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

    private Integer no;

    private String degree;

    private String name;

    private String department;

    private String deputationUniversity;

    private Byte deputationPeriod;


    private List<Days> deputationDays;


    private Integer departmentRecordNum;
    private Date departmentDate;
    private Byte departmentAccept;


    private Integer facultyRecordNum;
    private Date facultyDate;
    private Byte facultyAccept;


    private Integer universityRecordNum;
    private Date universityDate;
    private Byte universityAccept;

    private String notes;
}
