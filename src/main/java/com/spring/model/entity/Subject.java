package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer num;
    private String  head;

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private List<Decision> decision;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "special_id")
    @JsonIgnore
    private Special special;


}