package com.spring.model.dto.phonedirector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TeachingPhoneDirectorDto {
    private Integer id;

    private Integer serial;

    private String name;

    private String job;

    private String department;

    private String phone;

    private String mobile;

    private String address;

    private String email;

    private String notes;

}
