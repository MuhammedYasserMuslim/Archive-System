package com.spring.model.dto.imports;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.exports.ExportDto;
import com.spring.model.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImportDto {

    private Short id; // رقم الملف
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date incomeDate; //تاريخ الورود
    private Byte numberOfAttachments; //عدد الموافقات

    @JsonIgnore
    private List<Image> images;
    private List<String> paths;
    private String sender; //الجهة الوارد منها الخطاب
    private Short incomingLetterNumber; //رقم الخطاب الوارد
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomingLetterDate;//تاريخ الخطاب الوارد
    private String summary;//ملخص الخطاب
    private String recipientName;//توقيع المستلم
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recipientDate; // تاريخ الاستلام

    //Export
    @JsonBackReference
    private ExportDto export;
    private Short responseNumber; // رقم الرد
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate; //تاريخ الرد
    private String responseSide; //الجهة الصادر اليها الخطاب

    // ArchiveFileDto
    @JsonBackReference
    private ArchiveFileDto archiveFile;
    private Short archiveId;
    private Byte typeNumber;
    private Long num;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectResponseDate;

    public Byte getNumberOfAttachments() {
        return (byte)this.images.size();
    }
    public List<String> getPaths() {
        List<String> path = new ArrayList<>();
        for (Image im : this.images) {
            path.add(im.getImagePath());
        }
        return path;
    }
}

