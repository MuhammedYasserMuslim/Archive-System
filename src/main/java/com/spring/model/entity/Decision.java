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

    @Column(name = "num", nullable = false)
    private Integer num;

    @Column(name = "summary" ,nullable = false)
    private String summary;

    @Column(name = "qarar" ,nullable = false)
    private String qarar;

    @ManyToOne(cascade = CascadeType.MERGE ,fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id" ,referencedColumnName = "id")
    @JsonIgnore
    private Subject subject;
}
