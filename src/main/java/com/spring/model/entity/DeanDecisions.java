package com.spring.model.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dean_decisions")
public class DeanDecisions extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "no" , nullable = false)
    private Integer no;

    @Column(name = "date" , nullable = false)
    private Date date;

    @Column(name = "summary" , nullable = false)
    private String summary;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "archive_file_id", referencedColumnName = "id", nullable = false)
    private ArchiveFile archiveFile;

    @OneToMany(mappedBy = "deanDecisions")
    private List<Image> images;

    public DeanDecisions(Integer id) {
        this.id = id;
    }
}
