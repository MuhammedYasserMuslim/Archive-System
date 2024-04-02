package com.spring.model.dto.deandecisions;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DeanDecisionsDto {


    private Integer id;

    private Integer no;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String summary;//ملخص الخطاب

    private String createdBy;

    @JsonBackReference
    private ArchiveFileDto archiveFile;

    private Short archiveId;
    private Byte typeNumber;
    private Short num;
}
