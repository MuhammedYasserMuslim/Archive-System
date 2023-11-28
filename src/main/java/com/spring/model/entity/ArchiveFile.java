package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.model.enums.FileType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "archivefile")
public class ArchiveFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;     // primary key

    @Column(name = "num", nullable = false)
    private Short num;//   رقم الملف

    @Column(name = "name", unique = true, nullable = false, length = 255)
    private String name;// اسم الملف

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FileType fileType;// نوع الملف

    @Column(name = "type_number", nullable = false)
    private Byte typeNumber;// رفم نوع الملف

    @OneToMany(mappedBy = "archiveFile")
    @JsonIgnore
    private Set<Export> exports;


    @OneToMany(mappedBy = "archiveFile")
    @JsonIgnore
    private Set<Import> imports;


}
