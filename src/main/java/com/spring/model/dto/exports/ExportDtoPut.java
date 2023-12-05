package com.spring.model.dto.exports;

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
public class ExportDtoPut {

    private Short id;
    private String receiver; //الجهة الصادر منها الخطاب
    private String summary;//ملخص الخطاب
    private String recipientName;// المستلم
    private Short urgentNum;// رقم الاستعجلات
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate; //تاريخ الاستعجالات

    // ArchiveFileDto
    private ArchiveFileDto archiveFile;

    private Short archiveId;
    private Byte typeNumber;
    private Long num;
}
