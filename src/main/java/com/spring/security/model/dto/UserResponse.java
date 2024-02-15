package com.spring.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Byte id;
    private String username;
    private int isActive;
    private String firstName;
    private String lastName;
    private String roles;


}
