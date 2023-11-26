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
@Table(name = "imports")
public class Import extends BaseEntity<Short> {

    //    private Short id; // رقم الملف

    @Column(name = "income_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomeDate; //تاريخ الورود

    @Column(name = "number_of_approve")
    private Short numberOfApprove; //عدد الموافقات

    @Column(name = "sender")
    private String sender; //الجهة الوارد منها الخطاب

    @Column(name = "incoming_letter_number")
    private Short incomingLetterNumber; //رقم الخطاب الوارد

    @Column(name = "incoming_letter_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomingLetterDate;//تاريخ الخطاب الوارد

    @Column(name = "summary")
    private String summary;//ملخص الخطاب

    @Column(name = "recipient_name")
    private String recipientName;//توقيع المستلم

    @Column(name = "recipient_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date recipientDate; // تاريخ الاستلام


    @OneToOne
    @JoinColumn(name = "Response_number")
    private Export export;


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE})
    @JoinColumn(name = "archive_file_id")
    private ArchiveFile archiveFile;


}
