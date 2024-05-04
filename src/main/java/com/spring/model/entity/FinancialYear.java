package com.spring.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "financial_year")
public class FinancialYear {

    @Id
    @Column(name = "financial_year")
    private String year;

    @Column(name = "from_date")
    private Date from;

    @Column(name = "to_date")
    private Date to;
}
