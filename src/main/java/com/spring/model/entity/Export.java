package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exports")
public class Export extends BaseEntity<Short>{

    //private Short id;//رقم

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date; //تاريخ

    @Column(name = "number_of_approve")
    private Long numberOfApprove; //عدد الموافقات

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

//    @Column(name = "response_number")
//    private Short responseNumber; // رقم الرد
//
//    @Column(name = "response_date")
//    private Date responseDate; //تاريخ الرد
//
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "response_number")
    private Import aimport;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "archive_file_id")
    private ArchiveFile archiveFile;
}
