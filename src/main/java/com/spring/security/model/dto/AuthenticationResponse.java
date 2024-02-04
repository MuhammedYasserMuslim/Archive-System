package com.spring.security.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private Long id;
    private String username;
    private String Token;
    private String name;
    private String imagePath;


}
