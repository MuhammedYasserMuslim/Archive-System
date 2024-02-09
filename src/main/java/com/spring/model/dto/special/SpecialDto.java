package com.spring.model.dto.special;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.entity.Special;
import com.spring.model.entity.Subject;
import com.spring.model.mapper.SubjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialDto {


    private Long id;
    private String name;
    private String summary;

    @JsonBackReference
    private List<Subject> subject;


    private List<SpecialDto> subjects;


    @JsonBackReference
    private ArchiveFileDto archiveFile;

    private Short archiveId;
    private Byte typeNumber;
    private Long num;


}
