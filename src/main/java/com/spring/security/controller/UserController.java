package com.spring.security.controller;

import com.spring.security.model.dto.ChangePassword;
import com.spring.security.model.dto.UserResponse;
import com.spring.security.model.dto.UserUpdate;
import com.spring.security.model.entity.AppUser;
import com.spring.security.model.mapper.UserMapper;
import com.spring.security.services.UserServices;
import com.spring.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;
    private final UserMapper userMapper;
    private final FileUploadService fileUploadService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> findALlUsers() {
        return new ResponseEntity<>(userServices.findALlUsers(), HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> findById(@RequestParam byte id) {
        return new ResponseEntity<>(userServices.findById(id), HttpStatus.OK);
    }

    @PutMapping("/user")
    public ResponseEntity<?> update(@RequestBody UserUpdate dto, @RequestParam byte id) {
        userServices.update(dto, id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/changePassword")
    private ResponseEntity<?> changePassword(@RequestParam String username, @RequestBody ChangePassword password) {
        userServices.changePassword(username, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/activated")
    public ResponseEntity<?> activeUser(@RequestParam String username) {
        userServices.activeUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/unactivated")
    public ResponseEntity<?> unActiveUser(@RequestParam String username) {
        userServices.unActiveUser(username);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-image")
    public ResponseEntity<?> addUserImage(@RequestParam String username, @RequestParam int id, @RequestParam String pathType
            , @RequestParam MultipartFile file) {
        AppUser user = userServices.findByUserName(username);
        String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType);
        user.setImagePath("assets\\".concat(pathType.concat("\\").concat(fileName)));
        userServices.update(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
