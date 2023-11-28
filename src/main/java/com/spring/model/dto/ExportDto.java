package com.spring.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExportDto {


    private Short id;   //رقم

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date; //تاريخ
    private Byte numberOfAttachments; //عدد الموافقات
    private String receiver; //الجهة الصادر منها الخطاب
    private String summary;//ملخص الخطاب
    private String recipientName;// المستلم
    private Short urgentNum;// رقم الاستعجلات
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate; //تاريخ الاستعجالات

    // Import
    private ImportDto aimport;

    private Short responseNumber; // رقم الرد
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate; //تاريخ الرد


    // ArchiveFileDto
    private ArchiveFileDto archiveFile;

    private Short archiveId;
    private Byte typeNumber;
    private Long num;


}
