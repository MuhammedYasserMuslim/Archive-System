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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name" , nullable = false , unique = true)
    private String name;

    @Column(name = "image_path", nullable = false, unique = true)
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "export_id", referencedColumnName = "id")
    @JsonIgnore
    private Export export;

    @ManyToOne
    @JoinColumn(name = "import_id", referencedColumnName = "id")
    @JsonIgnore
    private Import anImport;

    @ManyToOne
    @JoinColumn(name = "special_id", referencedColumnName = "id")
    @JsonIgnore
    private Special special;

    @ManyToOne
    @JoinColumn(name = "dean_decision_id", referencedColumnName = "id")
    @JsonIgnore
    private DeanDecisions deanDecisions;
}

