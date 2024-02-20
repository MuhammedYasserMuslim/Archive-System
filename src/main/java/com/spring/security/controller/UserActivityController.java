package com.spring.security.controller;


import com.spring.security.model.dto.UserActivity;
import com.spring.security.services.UserActivityServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserActivityController {

    private final UserActivityServices userActivityServices;
    @GetMapping("/user-activity")
    public List<UserActivity> findUsersActivity(){
        return userActivityServices.findUsersActivity();
    }

}
