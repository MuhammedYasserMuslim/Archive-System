package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "special")
public class Special extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no" , nullable = false )
    private Integer no;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "import_num")
    private Integer importNum;

    @Column(name = "summary",nullable = false)
    private String summary;

    @Column(name = "income_date" , nullable = false)
    private Date incomeDate;

    @Column(name = "number_of_attachments", nullable = false)
    private Byte numberOfAttachments;

    @Column(name = "sender")
    private String sender;

    @Column(name = "notes")
    private String notes;

    @OneToMany(mappedBy = "special")
    private List<Image> images;

    @OneToMany(mappedBy = "special", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH , CascadeType.REFRESH}, fetch = FetchType.EAGER )
    private List<Subject> subject;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "archive_file_id",referencedColumnName = "id")
    private ArchiveFile archiveFile;

    public Special(int id) {
        this.id = id;
    }
}
