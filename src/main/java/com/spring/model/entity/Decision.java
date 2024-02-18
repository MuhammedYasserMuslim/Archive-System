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
@Table(name = "decisions")
@AllArgsConstructor
@NoArgsConstructor
public class Decision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    private Integer num;
    @Column(name = "summary")
    private String summary;

    private String qarar;

    @ManyToOne(cascade = CascadeType.MERGE ,fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    private Subject subject;
}
