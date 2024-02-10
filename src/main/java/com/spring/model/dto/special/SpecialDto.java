package com.spring.model.dto.special;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.entity.Image;
import com.spring.model.entity.Subject;
import jakarta.persistence.Column;
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
public class SpecialDto {


    private Long id;
    private Long importNum;
    private String summary;
    private Byte numberOfAttachments;
    @JsonBackReference
    private List<Image> images;
    private List<String> paths;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date incomeDate;
    private String sender;

    @JsonBackReference
    private List<Subject> subject;


    private List<SubjectDto> subjects;


    @JsonBackReference
    private ArchiveFileDto archiveFile;

    private Short archiveId;
    private Byte typeNumber;
    private Short num;


    public List<SubjectDto> getSubjects() {
        List<SubjectDto> dtos = new ArrayList<>();
        for (Subject subject1 :this.subject) {
            SubjectDto dto = new SubjectDto(subject1.getSummary());
            dtos.add(dto);
        }
        return dtos;
    }

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
