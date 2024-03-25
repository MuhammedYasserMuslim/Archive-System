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
@Table(name = "exports")
public class Export extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//رقم

    @Column(name = "no", nullable = false)
    private Integer no;

    @Column(name = "date" ,nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date; //تاريخ

    @Column(name = "number_of_attachments", nullable = false)
    private Byte numberOfAttachments;

    @OneToMany(mappedBy = "export")
    private List<Image> images;

    @Column(name = "receiver")
    private String receiver; //الجهة الصادر منها الخطاب

    @Column(name = "summary" ,nullable = false)
    private String summary;//ملخص الخطاب

    @Column(name = "recipient_name")
    private String recipientName;// المستلم


    @Column(name = "urgent_num")
    private Integer urgentNum;// رقم الاستعجلات

    @Column(name = "urgent_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date urgentDate; //تاريخ الاستعجالات

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "response_id", referencedColumnName = "id")
    private Import aimport; //رقم الرد / تاريخ الرد


    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "archive_file_id", referencedColumnName = "id" , nullable = false)
    private ArchiveFile archiveFile;


    public Export(int id) {
        this.id = id;
    }


}
