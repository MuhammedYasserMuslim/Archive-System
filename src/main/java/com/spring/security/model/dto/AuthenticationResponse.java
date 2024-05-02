package com.spring.security.model.dto;

import com.spring.configuration.Global;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class AuthenticationResponse {

    private Byte id;
    private String username;
    private String Token;
    private String name;
    private String imagePath;
    private String roles;

    public String getImagePath() {
        return Global.BASE_URL.concat(imagePath);
    }
}
