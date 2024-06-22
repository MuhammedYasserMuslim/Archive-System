package com.spring.security.controller;

import com.spring.exception.UserExistedException;
import com.spring.security.jwt.JwtServices;
import com.spring.security.model.dto.AuthenticationRequest;
import com.spring.security.model.dto.AuthenticationResponse;
import com.spring.security.model.dto.Pass;
import com.spring.security.model.dto.UserRequest;
import com.spring.security.model.entity.AppUser;
import com.spring.security.services.AuthenticationService;
import com.spring.security.services.UserServices;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class SecurityController {

    private final AuthenticationService authenticationService;
    private final UserServices userServices;
    private final JwtServices jwtServices;


    @GetMapping("is-token-valid")
    public ResponseEntity<?> isTokenValid(@RequestParam String token, @RequestParam String username) {
        try {
            return jwtServices.isTokenValid(token.substring(7), username)
                    ? new ResponseEntity<>(1, HttpStatus.OK)
                    : new ResponseEntity<>(0, HttpStatus.UNAUTHORIZED);
        } catch (JwtException e) {
            return new ResponseEntity<>(0, HttpStatus.UNAUTHORIZED);
        }

    }


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserRequest dto) {
        userServices.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("user/changePassword-admin")
    private ResponseEntity<?> changePassword(@RequestParam byte id, @RequestBody Pass password) {
        userServices.changePassword(id, password);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
