package com.spring.security.controller;

import com.spring.exception.UserExistedException;
import com.spring.security.model.dto.AuthenticationRequest;
import com.spring.security.model.dto.AuthenticationResponse;
import com.spring.security.model.dto.UserRequest;
import com.spring.security.model.dto.UserResponse;
import com.spring.security.model.entity.AppUser;
import com.spring.security.model.mapper.UserMapper;
import com.spring.security.services.AuthenticationService;
import com.spring.security.services.UserServices;
import com.spring.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
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
    public ResponseEntity<UserResponse> findById(@RequestParam Long id){
        if (userServices.findById(id).isPresent())
            return new ResponseEntity<>(userMapper.mapToDto(userServices.findById(id).get()),HttpStatus.FOUND);
        else
            throw new UsernameNotFoundException("invalid User Id  :" + id);
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

    @PutMapping("user/user")
    public ResponseEntity<?> update(@RequestBody UserRequest dto) {
        userServices.update(dto);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



    @PostMapping("register/add-image")
    public ResponseEntity<?> addUserImage(@RequestParam String username, @RequestParam Long id, @RequestParam String pathType
            , @RequestParam MultipartFile file) {
        AppUser user = userServices.findByUserName(username);
        String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType);
        user.setImagePath(fileUploadService.getBasePath().concat(pathType.concat("\\").concat(fileName)));
        userServices.update(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

