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
@Table(name = "archive_file")
public class ArchiveFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "num", nullable = false)
    private Short num;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private FileType fileType;

    @Column(name = "type_number", nullable = false)
    private Byte typeNumber;

    @OneToMany(mappedBy = "archiveFile")
    @JsonIgnore
    private Set<Export> exports;

    @OneToMany(mappedBy = "archiveFile")
    @JsonIgnore
    private Set<Import> imports;

    @OneToMany(mappedBy = "archiveFile")
    @JsonIgnore
    private Set<Special> specials;

    @OneToMany(mappedBy = "archiveFile")
    @JsonIgnore
    private Set<DeanDecisions> deanDecisions;


}
