package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "exports")
public class Export extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no", nullable = false)
    private Integer no;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(name = "number_of_attachments", nullable = false)
    private Byte numberOfAttachments;

    @OneToMany(mappedBy = "export")
    private List<Image> images;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "recipient_name")
    private String recipientName;

    @Column(name = "recipient_date")
    private Date recipientDate;

    @Column(name = "urgent_num")
    private Integer urgentNum;

    @Column(name = "urgent_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "response_id", referencedColumnName = "id")
    private Import aimport;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "archive_file_id", referencedColumnName = "id", nullable = false)
    private ArchiveFile archiveFile;

    @Column(name = "saved", nullable = false)
    private Integer saved ;


    public Export(int id) {
        this.id = id;
    }


}
