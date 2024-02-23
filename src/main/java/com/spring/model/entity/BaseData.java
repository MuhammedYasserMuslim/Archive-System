package com.spring.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@Entity
@Table(name = "base_data")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseData {

    @Id
    private Byte id;

    private String imagesPath;
}
