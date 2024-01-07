package com.spring.security.services;

import com.spring.security.dto.AuthenticationRequest;
import com.spring.security.dto.AuthenticationResponse;
import com.spring.security.entity.AppUser;
import com.spring.security.jwt.JwtServices;
import com.spring.security.repository.UserRepository;
import com.spring.security.security.AppUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final JwtServices jwtService;
    private final AuthenticationManager authenticationManager;

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
                .Token(jwtToken)
                .build();
    }
}