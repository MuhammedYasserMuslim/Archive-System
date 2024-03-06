package com.spring.model.dto.exports;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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


    private Integer id;   //رقم

    private Integer no;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date date; //تاريخ
    private Byte numberOfAttachments; //عدد الموافقات

    @JsonBackReference
    private List<Image> images;
    private List<String> paths;

    private String receiver; //الجهة الصادر منها الخطاب
    private String summary;//ملخص الخطاب
    private String recipientName;// المستلم

    private Short urgentNum;// رقم الاستعجلات
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate; //تاريخ الاستعجالات

    // Import
    @JsonBackReference
    private ImportDto aimport;
    private Integer responseNumber; // رقم الرد
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate; //تاريخ الرد

    // ArchiveFileDto
    @JsonBackReference
    private ArchiveFileDto archiveFile;

    private Short archiveId;
    private Byte typeNumber;
    private Short num;

    public Byte getNumberOfAttachments() {
        return (byte) this.images.size();
    }

    public List<String> getPaths() {
        List<String> path = new ArrayList<>();
        for (Image im : this.images) {
            path.add(im.getImagePath());
        }
        return path;
    }
}
