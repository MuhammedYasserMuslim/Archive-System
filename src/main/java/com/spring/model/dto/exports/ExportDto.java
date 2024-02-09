package com.spring.model.dto.exports;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.dto.images.Img;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.imports.ImportDto;
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
public class ExportDto {


    private Short id;   //رقم

    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date date; //تاريخ
    private Byte numberOfAttachments; //عدد الموافقات
    @JsonIgnore
    private List<Image> images;
    private List<Img> paths;
    private String receiver; //الجهة الصادر منها الخطاب
    private String summary;//ملخص الخطاب
    private String recipientName;// المستلم

    private Short urgentNum;// رقم الاستعجلات
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate; //تاريخ الاستعجالات

    // Import
    @JsonBackReference
    private ImportDto aimport;
    private Short responseNumber; // رقم الرد
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate; //تاريخ الرد

    // ArchiveFileDto
    @JsonBackReference
    private ArchiveFileDto archiveFile;

    private Short archiveId;
    private Byte typeNumber;
    private Long num;

    public Byte getNumberOfAttachments() {
        return (byte)this.images.size();
    }

    public List<Img> getPaths() {
        List<Img> imgs = new ArrayList<>();
        for (Image image : this.images) {
            Img img = new Img(image.getId(), image.getImagePath());
            imgs.add(img);
        }
        return imgs;
    }
}
