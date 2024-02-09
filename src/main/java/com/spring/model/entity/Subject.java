package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
public class Subject extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;


    private String summary;


    @ManyToOne(cascade = {CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH})
    @JoinColumn(name = "specail_id")
    @JsonIgnore
    private Special special;

}