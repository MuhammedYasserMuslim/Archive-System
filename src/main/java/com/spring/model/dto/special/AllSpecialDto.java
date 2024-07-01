package com.spring.model.dto.special;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.entity.Image;
import com.spring.model.entity.Subject;
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
public class AllSpecialDto {

    private Integer id;

    private Integer no;

    private String fileType;

    private Integer importNum;

    private String summary;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomeDate;

    private String sender;

    @JsonBackReference
    private List<Image> images;

    private List<String> paths;

    private Byte numberOfAttachments;

    private Byte numberOfImages;

    @JsonBackReference
    private List<Subject> subject;

    private List<SubjectDto> subjects;

    private String createdBy;

    @JsonBackReference
    private ArchiveFileDto archiveFile;

    private Short archiveId;

    private Byte typeNumber;

    private Short num;


    public List<SubjectDto> getSubjects() {
        List<SubjectDto> dtos = new ArrayList<>();
        for (Subject subject1 : this.subject)
            dtos.add(new SubjectDto(subject1.getNum(), subject1.getHead(), subject1.getDecision()));
        return dtos;
    }

    public Byte getNumberOfImages() {
        return (byte)this.images.size() ;
    }

    public List<String> getPaths() {
        List<String> path = new ArrayList<>();
        for (Image im : this.images)
            path.add(im.getImagePath());
        return path;
    }

    public Byte getTypeNumber() {
        return 3;
    }
}
