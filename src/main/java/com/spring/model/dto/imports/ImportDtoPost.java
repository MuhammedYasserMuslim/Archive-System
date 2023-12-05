package com.spring.model.dto.imports;

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
public class ImportDtoPost {



    private String sender; //الجهة الوارد منها الخطاب
    private Short incomingLetterNumber; //رقم الخطاب الوارد
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomingLetterDate;//تاريخ الخطاب الوارد
    private String summary;//ملخص الخطاب
    private String recipientName;//توقيع المستلم
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recipientDate; // تاريخ الاستلام

    // ArchiveFileDto
    private ArchiveFileDto archiveFile;
    private Short archiveId;
    private Byte typeNumber;
    private Long num;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectResponseDate;
}
