package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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
    private Short id;//رقم

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date date; //تاريخ

    @Column(name = "number_of_attachments")
    private Byte numberOfAttachments; //عدد الموافقات

    @Column(name = "receiver")
    private String receiver; //الجهة الصادر منها الخطاب

    @Column(name = "summary")
    private String summary;//ملخص الخطاب

    @Column(name = "recipient_name")
    private String recipientName;// المستلم


    @Column(name = "urgent_num")
    private Short urgentNum;// رقم الاستعجلات

    @Column(name = "urgent_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate; //تاريخ الاستعجالات

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "response_id")
    private Import aimport; //رقم الرد / تاريخ الرد


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "archive_file_id")
    private ArchiveFile archiveFile;


}
