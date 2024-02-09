package com.spring.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;     // primary key

    @Column(name = "name")
    private String name;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne
    @JoinColumn(name = "export_id")
    @JsonIgnore
    private Export export;

    @ManyToOne
    @JoinColumn(name = "import_id")
    @JsonIgnore
    private Import anImport;

    @ManyToOne
    @JoinColumn(name = "specail_id")
    @JsonIgnore
    private Special special;
}

