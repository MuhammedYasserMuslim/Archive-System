package com.spring.model.dto.exports;

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
public class ExportDtoPost {

    private Integer id;
    private Integer no;
    private String receiver;
    private Byte numberOfAttachments;
    private Date date;
    private String summary;
    private String recipientName;
    private Date recipientDate;
    private Integer saved;

    private Byte secure = 0;


    private ArchiveFileDto archiveFile;
    private Byte typeNumber;
    private Short num;
}
