package com.spring.security.services;

import com.spring.security.model.dto.UserRequest;
import com.spring.security.model.dto.UserResponse;
import com.spring.security.model.entity.AppUser;
import com.spring.security.model.entity.Authority;
import com.spring.security.model.mapper.UserMapper;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;

    public List<UserResponse> findALlUsers() {
        List<AppUser> users = userRepository.findAll();
        List<UserResponse> dtos = new ArrayList<>();
        for (AppUser user : users) {
            dtos.add(userMapper.mapToDto(user));
        }
        return dtos;
    }
    public List<AppUser> findAll(){
        return userRepository.findAll();
    }

    public void save(UserRequest dto) {
        this.save(userMapper.mapToEntity(dto));
    }

    public void save(AppUser user) {
        List<Authority> authorities = authorityService.findAll();
        Set<Authority> set = new HashSet<>();
        set.add(authorities.get(2));
        user.setAuthorities(set);
        user.setIsActive(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public AppUser findByUserName(String username) {
        return userRepository.findByUsername(username).get();
    }


}
