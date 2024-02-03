package com.spring.security.controller;

import com.spring.exception.UserExistedException;
import com.spring.security.model.dto.AuthenticationRequest;
import com.spring.security.model.dto.AuthenticationResponse;
import com.spring.security.model.dto.UserRequest;
import com.spring.security.model.dto.UserResponse;
import com.spring.security.model.entity.AppUser;
import com.spring.security.services.AuthenticationService;
import com.spring.security.services.UserServices;
import com.spring.services.FileUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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



   // @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("user/users")
    public ResponseEntity<List<UserResponse>> findALlUsers(){
        return new ResponseEntity<>(userServices.findALlUsers(),HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }



    @PostMapping("register")
    public ResponseEntity<?> register(@RequestPart("dto") UserRequest dto, @RequestParam Long id, @RequestParam String pathType
            , @RequestParam MultipartFile file) {
        for (AppUser users : userServices.findAll()) {
            if (users.getUsername().equals(dto.getUsername())) {
                throw new UserExistedException("This username ( " + dto.getUsername() + " ) is exist");
            }
        }
        String fileName = fileUploadService.storeFile(fileUploadService.convertMultiPartFileToFile(file), id, pathType);
        dto.setImagePath(fileUploadService.getBasePath().concat( pathType.concat("\\").concat(fileName)));
        userServices.save(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}

