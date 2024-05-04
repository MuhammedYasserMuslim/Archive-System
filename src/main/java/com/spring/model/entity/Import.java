package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imports")
public class Import extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // رقم الملف

    @Column(name = "no", nullable = false)
    private Integer no;

    @Column(name = "income_date", nullable = false)
    private Date incomeDate; //تاريخ الورود

    @OneToMany(mappedBy = "anImport")
    private List<Image> images;

    @Column(name = "sender")
    private String sender; //الجهة الوارد منها الخطاب

    @Column(name = "incoming_letter_number")
    private Integer incomingLetterNumber; //رقم الخطاب الوارد

    @Column(name = "incoming_letter_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomingLetterDate;//تاريخ الخطاب الوارد

    @Column(name = "summary", nullable = false)
    private String summary;//ملخص الخطاب

    @Column(name = "recipient_name")
    private String recipientName;//توقيع المستلم

    @Column(name = "recipient_date")
    private Date recipientDate; // تاريخ الاستلام

    @Column(name = "number_of_attachments", nullable = false )
    private Byte numberOfAttachments; //عدد الموافقات

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "response_id", referencedColumnName = "id")
    private Export export;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "archive_file_id", referencedColumnName = "id", nullable = false)
    private ArchiveFile archiveFile;

    @Column(name = "expect_response_date")
    private Date expectResponseDate;

    @Column(name = "saved" , nullable = false)
    private Integer saved;

    public Import(int id) {
        this.id = id;
    }


}
