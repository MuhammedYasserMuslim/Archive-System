package com.spring.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.spring.security.model.entity.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "days")
public class Days {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String day;

    @ManyToMany(mappedBy = "deputationDays")
    @JsonBackReference
    private Set<Deputation> deputations = new HashSet<>();

    public Days(String day) {
        this.day = day;
    }
}
