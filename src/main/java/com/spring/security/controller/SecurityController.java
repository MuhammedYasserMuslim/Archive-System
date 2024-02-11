package com.spring.security.controller;

import com.spring.exception.UserExistedException;
import com.spring.security.model.dto.*;
import com.spring.security.model.entity.AppUser;
import com.spring.security.model.mapper.UserMapper;
import com.spring.security.services.AuthenticationService;
import com.spring.security.services.UserServices;
import com.spring.services.FileUploadService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
@Validated
public class SecurityController {

    private final AuthenticationService authenticationService;
    private final UserServices userServices;
    private final FileUploadService fileUploadService;
    private final UserMapper userMapper;


    // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("user/users")
    public ResponseEntity<List<UserResponse>> findALlUsers() {
        return new ResponseEntity<>(userServices.findALlUsers(), HttpStatus.OK);
    }

    @GetMapping("user/user")
    public ResponseEntity<UserResponse> findById(@RequestParam byte id) {
        if (userServices.findById(id).isPresent())
            return new ResponseEntity<>(userMapper.mapToDto(userServices.findById(id).get()), HttpStatus.OK);
        else
            throw new UsernameNotFoundException("invalid User Id  :" + id);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequest dto) {
        for (AppUser users : userServices.findAll()) {
            if (users.getUsername().equals(dto.getUsername())) {
                throw new UserExistedException("This username ( " + dto.getUsername() + " ) is exist");
            }
        }
        userServices.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("user/user")
    public ResponseEntity<?> update(@RequestBody UserUpdate dto, @RequestParam byte id) {
        userServices.update(dto, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


    @PostMapping("register/add-image")
    public ResponseEntity<?> addUserImage(@RequestParam String username, @RequestParam int id, @RequestParam String pathType
            , @RequestParam MultipartFile file) {
        AppUser user = userServices.findByUserName(username);
        String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType);
        user.setImagePath("assets\\".concat(pathType.concat("\\").concat(fileName)));
        userServices.update(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("user/changePassword")
    private ResponseEntity<?> changePassword(@RequestParam String username, @RequestBody ChangePassword password) {
        userServices.changePassword(username, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("user/changePassword-admin")
    private ResponseEntity<?> changePassword(@RequestParam String username, @RequestParam String password) {
        for (AppUser users : userServices.findAll()) {
            if (users.getUsername().equals(username)) {
                userServices.changePassword(username, password);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
        }
        throw new UserExistedException("This username ( " + username + " ) is exist");
    }


    @PostMapping("user/activated")
    public ResponseEntity<?> activeUser(@RequestParam String username) {
        userServices.activeUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("user/unactivated")
    public ResponseEntity<?> unActiveUser(@RequestParam String username) {
        userServices.unActiveUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

