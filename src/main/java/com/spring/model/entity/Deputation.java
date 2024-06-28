package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deputation")
public class Deputation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "no")
    private Integer no;

    @Column(name = "degree")
    private String degree;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "deputation_university")
    private String deputationUniversity;

    @Column(name = "deputation_period")
    private Byte deputationPeriod;

    //موافقة مجلس الكلية وديه تحتها 3 head  موضوع رقم تاريخ
    //موافقة مجلس الجامعة  تحتها رقم تاريخ

    @JoinTable(
            name = "deputation_days",
            joinColumns = @JoinColumn(name = "deputation_id"),
            inverseJoinColumns = @JoinColumn(name = "days_id"))
    @OrderColumn(name = "id")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Days> deputationDays;


    private String notes;




}
