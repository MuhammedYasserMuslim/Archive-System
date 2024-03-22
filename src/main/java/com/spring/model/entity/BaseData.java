package com.spring.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.UUID;

@Entity
@Table(name = "base_data")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Byte id;

    @Column(name = "images_path")
    private String imagesPath;


}
