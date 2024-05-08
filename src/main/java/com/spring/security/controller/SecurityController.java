package com.spring.security.controller;

import com.spring.exception.UserExistedException;
import com.spring.security.jwt.JwtServices;
import com.spring.security.model.dto.*;
import com.spring.security.model.entity.AppUser;
import com.spring.security.services.AuthenticationService;
import com.spring.security.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class SecurityController {

    private final AuthenticationService authenticationService;
    private final UserServices userServices;
    private final JwtServices jwtServices;


    @GetMapping("is-token-valid")
    public ResponseEntity<?> isTokenValid(@RequestParam String token, String username) {
        return jwtServices.isTokenValid(token.substring(7), username)
                ? new ResponseEntity<>(1,HttpStatus.OK)
                : new ResponseEntity<>(0,HttpStatus.UNAUTHORIZED);

    }


    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody UserRequest dto) {
        for (AppUser users : userServices.findAll()) {
            if (users.getUsername().equals(dto.getUsername())) {
                throw new UserExistedException("This username ( " + dto.getUsername() + " ) is exist");
            }
        }
        userServices.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("user/changePassword-admin")
    private ResponseEntity<?> changePassword(@RequestParam byte id, @RequestBody Pass password) {
        for (AppUser users : userServices.findAll()) {
            if (users.getId() == id) {
                userServices.changePassword(id, password);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        throw new UserExistedException("This username with id ( " + id + " ) is Not exist");
    }
}

