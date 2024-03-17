package com.spring.model.dto.special;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.entity.Subject;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SpecialDtoPost {
    private Integer id;
    private Integer no;
    private String summary;

    private Byte numberOfAttachments;
    private Date incomeDate;
    private String sender;
    private List<Subject> subjects;

    // ArchiveFileDto
    private ArchiveFileDto archiveFile;
    private Byte typeNumber;
    private Short num;
}
