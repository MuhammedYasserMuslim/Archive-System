package com.spring.model.dto.deandecisions;

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
public class DeanDecisionsDtoPost {

    private Integer id;
    private Integer no;
    private Date date;
    private String summary;//ملخص الخطاب

    // ArchiveFileDto
    private ArchiveFileDto archiveFile;
    private Byte typeNumber;
    private Short num;
}
