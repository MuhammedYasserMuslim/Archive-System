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
    private String receiver; //الجهة الصادر منها الخطاب
    private Byte numberOfAttachments;
    private Date date;
    private String summary;//ملخص الخطاب
    private String recipientName;// المستلم

    // ArchiveFileDto
    private ArchiveFileDto archiveFile;
    private Byte typeNumber;
    private Short num;
}
