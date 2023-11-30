package com.spring.model.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImportDto {

    private Short id; // رقم الملف

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomeDate; //تاريخ الورود

    private Byte numberOfAttachments; //عدد الموافقات

    private String sender; //الجهة الوارد منها الخطاب

    private Short incomingLetterNumber; //رقم الخطاب الوارد

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomingLetterDate;//تاريخ الخطاب الوارد

    private String summary;//ملخص الخطاب

    private String recipientName;//توقيع المستلم

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recipientDate; // تاريخ الاستلام

    //Export
    private ExportDto export;
    private Short responseNumber; // رقم الرد
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate; //تاريخ الرد
    private String responseSide; //الجهة الصادر اليها الخطاب


    // ArchiveFileDto
    private ArchiveFileDto archiveFile;
    private Short archiveId;
    private Byte typeNumber;
    private Long num;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectResponseDate;
}

