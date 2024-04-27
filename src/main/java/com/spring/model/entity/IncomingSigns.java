package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@Entity
@Table(name = "incoming_signs")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class IncomingSigns extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;  //رقم الأشارة


    @Column(name = "date" , nullable = false)
    private Date date; // تاريخ الاشارة
    @Column(name = "sender" , nullable = false)
    private String sender; //الجهة الوارد منها الاشارة
    @Column(name = "via")
    private String via; //عن طريق

    @Column(name = "university_year")
    private String universityYear ;

    @Column(name = "sign_informer")
    private String signInformer;//مبلغ الاشارة
    @Column(name = "sign_informer_self")
    private String signInformerSelf;//صفة مبلغ الاشارة
    @Column(name = "sign_informer_phone")
    private String signInformerPhone;//موبايل مبلغ الاشارة


    @Column(name = "summary"   ,nullable = false )
    private String summary;//محتوي الاشارة
    @Column(name = "sign_signature")
    private String signSignature;//توقيع الاشارة
    @Column(name = "sign_self")
    private String signSelf;//صفة الأشارة


    @Column(name = "sign_recipient_name")
    private String signRecipientName;//اسم مستلم الاشارة

    @Column(name = "sign_recipient_self")
    private String signRecipientSelf;//صفة مستلم الاشارة

    @Column(name = "sign_recipient_date")
    private Date signRecipientDate;//تاريخ استلام الاشارة

    @Column(name = "sign_excuted_name")
    private String signExcutedName;//اسم الامختص بتنفيذ الأشارة
    @Column(name = "sign_excuted_self")
    private String signExcutedSelf;//صفة المختص بتنفيذ الأشارة
    @Column(name = "sign_excuted_date")
    private Date SignExecutionDate;//تاريخ تنفيذ الأشارة
    @Column(name = "depend")
    private String depend; //يعتمد


}
