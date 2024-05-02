package com.spring.model.dto.exports;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.configuration.Global;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Date date; //تاريخ
    private Byte numberOfAttachments; //عدد الموافقات

    private String createdBy;

    @JsonBackReference
    private List<Image> images;
    private List<String> paths;

    private boolean isHasResponse;
    private boolean isHasUrgent;

    private String receiver; //الجهة الصادر منها الخطاب
    private String summary;//ملخص الخطاب
    private String recipientName;// المستلم
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recipientDate;//تاريخ الاستلام
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

    private Integer saved;

    public List<String> getPaths() {
        List<String> path = new ArrayList<>();
        for (Image im : this.images)
            path.add(Global.baseUrl.concat(im.getImagePath()));
        return path;
    }


    public boolean isHasResponse() {
        return this.responseNumber == null;
    }

    public boolean isHasUrgent() {
        return this.urgentNum == null;
    }

    public boolean isHasSpecial(){return this.saved == 0;}
}
