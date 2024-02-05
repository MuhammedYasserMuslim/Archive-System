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

import java.util.*;

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
    public Optional<AppUser> findById(Long id){
        return userRepository.findById(id);
    }

    public void save(UserRequest dto) {
        this.save(userMapper.mapToEntity(dto));
    }

    public void save(AppUser user) {
        List<Authority> authorities = authorityService.findAll();
        Set<Authority> set = new HashSet<>();
        set.add(authorities.get(2));
        user.setAuthorities(set);
        user.setIsActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public void update(AppUser user) {

        user.setAuthorities(userRepository.findById(user.getId()).get().getAuthorities());
        user.setIsActive(1);
        user.setPassword((user.getPassword()));
        this.userRepository.save(user);
    }

    public void update(UserRequest dto) {
        AppUser user =userMapper.mapToEntity(dto);
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setUsername(dto.getUsername());
        user.setAuthorities(userRepository.findById(dto.getId()).get().getAuthorities());
        user.setIsActive(userRepository.findById(dto.getId()).get().getIsActive());
        user.setImagePath(userRepository.findById(dto.getId()).get().getImagePath());
        this.userRepository.save(user);
    }

    public AppUser findByUserName(String username) {
        return userRepository.findByUsername(username).get();
    }


}
