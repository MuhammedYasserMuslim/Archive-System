package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imports")
public class Import extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // رقم الملف

    @Column(name = "income_date")
    @CreationTimestamp
    private Date incomeDate; //تاريخ الورود



    @OneToMany(mappedBy = "anImport")
    private List<Image> images ;

    @Column(name = "sender")
    private String sender; //الجهة الوارد منها الخطاب

    @Column(name = "incoming_letter_number")
    private Long incomingLetterNumber; //رقم الخطاب الوارد

    @Column(name = "incoming_letter_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date incomingLetterDate;//تاريخ الخطاب الوارد

    @Column(name = "summary")
    private String summary;//ملخص الخطاب

    @Column(name = "recipient_name")
    private String recipientName;//توقيع المستلم

    @Column(name = "recipient_date")
    private Date recipientDate; // تاريخ الاستلام


    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "response_id")
    private Export export;



    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "archive_file_id")
    private ArchiveFile archiveFile;

    @Column(name = "expect_response_date")
    private Date expectResponseDate;

    public Import(Long id) {
        this.id = id;
    }
}
