package com.spring.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserResponse {

    private Byte id;

    private String username;
    private int isActive;
    private String firstName;
    private String lastName;
    private String roles;
    private String phone;
    private String imagePath;

}
