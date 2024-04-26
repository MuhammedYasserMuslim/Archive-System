package com.spring.security.services;

import com.spring.security.jwt.JwtServices;
import com.spring.security.model.AppUserDetail;
import com.spring.security.model.dto.AuthenticationRequest;
import com.spring.security.model.dto.AuthenticationResponse;
import com.spring.security.model.entity.AppUser;
import com.spring.security.model.entity.Authority;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final JwtServices jwtService;
    private final AuthenticationManager authenticationManager;


    /**
     * @param request used tp authenticate user
     * @return Authentication
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        AppUser user = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(new AppUserDetail(user));

        return AuthenticationResponse.builder()
                .username(user.getUsername())
                .id(user.getId())
                .roles(this.getAuthority(user.getAuthorities()))
                .Token(jwtToken)
                .name(user.getFirstName().concat(" ").concat(user.getLastName()))
                .imagePath(user.getImagePath())
                .build();
    }

    /**
     * @param authorities find user role
     * @return userRole
     */
    private String getAuthority(Set<Authority> authorities) {
        List<Authority> list = new ArrayList<>(authorities);
        list.sort(Comparator.comparing(Authority::getId));
        for (Authority authority : list) {
            switch (authority.getName()) {
                case "ROLE_ADMIN" -> {
                    return "admin";
                }
                case "ROLE_MANAGER" -> {
                    return "manager";
                }
                case "ROLE_USER" -> {
                    return "user";
                }
            }
        }
        return null;
    }
}