package com.spring.model.entity;

import com.spring.model.enums.FileType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "archivefile")
public class ArchiveFile extends BaseEntity<Short> {

    //  private Short id       primary key

    @Column(name = "num", nullable = false)
    private Long num;//   رقم الملف

    @Column(name = "name", unique = true, nullable = false, length = 255)
    private String name;// اسم الملف

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FileType fileType;// نوع الملف

    @Column(name = "type_number", nullable = false)
    private Byte typeNumber;// رفم نوع الملف

    @OneToMany(mappedBy = "archiveFile")
    private List<Export> exports;

//
//    @OneToMany(mappedBy = "archiveFile")
//    @JsonIgnore
//    private List<Import> imports;


}
