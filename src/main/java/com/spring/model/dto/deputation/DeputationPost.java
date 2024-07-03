package com.spring.model.dto.deputation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class DeputationPost {

    private Integer id;


    private String degree;

    private String name;

    private String department;

    private String deputationUniversity;

    private Byte deputationPeriod;



    List<Integer> deputationDaysIds;


    private Integer departmentRecordNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date departmentDate;
    private Byte departmentAccept;


    private Integer facultyRecordNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date facultyDate;
    private Byte facultyAccept;


    private Integer universityRecordNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date universityDate;
    private Byte universityAccept;

    private String notes;
}
