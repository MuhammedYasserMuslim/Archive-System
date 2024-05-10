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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ImportDto {

    private Integer id;

    private Integer no;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomeDate;

    private Byte numberOfAttachments;

    private String createdBy;

    @JsonIgnore
    private List<Image> images;

    private List<String> paths;

    private String sender;

    private Short incomingLetterNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomingLetterDate;

    private String summary;

    private String recipientName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recipientDate;

    @JsonBackReference
    private ExportDto export;

    private Integer responseNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date responseDate;

    private String responseSide;

    @JsonBackReference
    private ArchiveFileDto archiveFile;

    private Short archiveId;

    private Byte typeNumber;

    private Short num;

    private Integer saved;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expectResponseDate;

    private boolean isHasResponse;


    public List<String> getPaths() {
        List<String> path = new ArrayList<>();
        for (Image im : this.images)
            path.add(im.getImagePath());
        return path;
    }

    public boolean isHasResponse() {
        return this.expectResponseDate != null && this.responseNumber == null;
    }
    public boolean isHasSpecial(){return this.saved == 0 && this.expectResponseDate ==null;}


}

