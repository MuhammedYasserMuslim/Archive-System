package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "incoming_signs")
public class IncomingSigns extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date date;

    @Column(name = "sender", nullable = false)
    private String sender;

    @Column(name = "via")
    private String via;

    @Column(name = "university_year")
    private String universityYear;

    @Column(name = "sign_informer")
    private String signInformer;

    @Column(name = "sign_informer_self")
    private String signInformerSelf;

    @Column(name = "sign_informer_phone")
    private String signInformerPhone;


    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "sign_signature")
    private String signSignature;

    @Column(name = "sign_self")
    private String signSelf;


    @Column(name = "sign_recipient_name")
    private String signRecipientName;

    @Column(name = "sign_recipient_self")
    private String signRecipientSelf;

    @Column(name = "sign_recipient_date")
    private Date signRecipientDate;

    @Column(name = "sign_excuted_name")
    private String signExcutedName;

    @Column(name = "sign_excuted_self")
    private String signExcutedSelf;

    @Column(name = "sign_excuted_date")
    private Date SignExecutionDate;

    @Column(name = "depend")
    private String depend;


}
