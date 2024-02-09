package com.spring.model.dto.special;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialDtoPost {



    private String name;
    private String summary;
    private String sender;
    private List<Subject> subjects;

    // ArchiveFileDto
    private ArchiveFileDto archiveFile;
    private Byte typeNumber;
    private Long num;
}
