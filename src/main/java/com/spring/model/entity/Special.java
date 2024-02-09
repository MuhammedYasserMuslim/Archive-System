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
@Table(name = "special")
@AllArgsConstructor
@NoArgsConstructor
public class Special extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String summary;

    @Column(name = "income_date")
    @CreationTimestamp
    private Date incomeDate;

    @Column(name = "sender")
    private String sender;

    @OneToMany(mappedBy = "special")
    private List<Image> images ;

    @OneToMany(mappedBy = "special" , cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH} ,fetch = FetchType.EAGER)
    private List<Subject> subject;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "archive_file_id")
    private ArchiveFile archiveFile;


}
