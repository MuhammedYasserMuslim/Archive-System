package com.spring.model.entity;

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
@Table(name = "deputation")
public class Deputation extends BaseEntity {

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

    @JoinTable(
            name = "deputation_days",
            joinColumns = @JoinColumn(name = "deputation_id"),
            inverseJoinColumns = @JoinColumn(name = "days_id"))
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Days> deputationDays;

    @Column(name = "department_record_num")
    private Integer departmentRecordNum;

    @Column(name = "department_date")
    private Date departmentDate;

    @Column(name = "department_accept")
    private Byte departmentAccept;

    @Column(name = "faculty_record_num")
    private Integer facultyRecordNum;

    @Column(name = "faculty_date")
    private Date facultyDate;

    @Column(name = "faculty_accept")
    private Byte facultyAccept;

    @Column(name = "university_record_num")
    private Integer universityRecordNum;

    @Column(name = "university_date")
    private Date universityDate;

    @Column(name = "university_accept")
    private Byte universityAccept;

    @Column(name = "notes")
    private String notes;
}
