package com.spring.security.controller;

import com.spring.exception.UserExistedException;
import com.spring.security.model.dto.AuthenticationRequest;
import com.spring.security.model.dto.AuthenticationResponse;
import com.spring.security.model.entity.AppUser;
import com.spring.security.services.AuthenticationService;
import com.spring.security.services.UserServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class SecurityController {

    private final AuthenticationService authenticationService;
    private final UserServices userServices;

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody AppUser user) {
        for (AppUser users : userServices.findAll()) {
            if (users.getUsername().equals(user.getUsername())) {
                throw new UserExistedException("This username ( " + user.getUsername() + " ) is exist");
            } else if (users.getEmail().equals(user.getEmail()))
                throw new UserExistedException("This email ( " + user.getEmail() + " ) is exist");
        }
        userServices.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

