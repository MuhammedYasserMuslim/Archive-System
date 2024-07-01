package com.spring.model.dto.exports;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.entity.Image;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllExportDto {


    private Integer id;

    private Integer no;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Byte numberOfAttachments;
    private Byte numberOfImages;

    private String createdBy;

    @JsonBackReference
    private List<Image> images;
    private List<String> paths;

    private boolean isHasResponse;
    private boolean isHasUrgent;

    private String receiver;
    private String summary;
    private String recipientName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recipientDate;
    private Short urgentNum;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate;


    @JsonBackReference
    private ImportDto aimport;
    private Integer responseNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate;


    @JsonBackReference
    private ArchiveFileDto archiveFile;

    private Short archiveId;
    private Byte typeNumber;
    private Short num;

    private Integer saved;

    public List<String> getPaths() {
        List<String> path = new ArrayList<>();
        for (Image im : this.images)
            path.add(im.getImagePath());
        return path;
    }

    public Byte getNumberOfImages() {
        return (byte)this.images.size() ;
    }



    public Byte getTypeNumber() {
        return 2;
    }
}
