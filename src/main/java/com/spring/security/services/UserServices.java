package com.spring.security.services;

import com.spring.exception.InvalidPasswordException;
import com.spring.exception.RecordNotFountException;
import com.spring.security.model.dto.*;
import com.spring.security.model.entity.AppUser;
import com.spring.security.model.entity.Authority;
import com.spring.security.model.mapper.UserMapper;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    /**
     * @return all users
     */
    public List<UserResponse> findALlUsers() {
        List<AppUser> users = userRepository.findAll();
        List<UserResponse> dtos = new ArrayList<>();
        for (AppUser user : users) {
            UserResponse dto = userMapper.mapToDto(user);
            dto.setRoles(getAuthority(user.getAuthorities()));
            dtos.add(dto);
        }
        return dtos;
    }

    /**
     * @return user authorities
     */
    private String getAuthority(Set<Authority> authorities) {
        List<Authority> list = new ArrayList<>(authorities);
        list.sort(Comparator.comparing(Authority::getId));
        for (Authority authority : list) {
            if (authority.getName().equals("ROLE_ADMIN"))
                return "admin";
            if (authority.getName().equals("ROLE_MANGER"))
                return "manager";
            if (authority.getName().equals("ROLE_USER"))
                return "user";
        }
        return null;
    }

    /**
     * @return All users
     */
    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    /**
     * @param id
     * @return user by id
     */
    public UserResponse findById(byte id) {
        if (userRepository.findById(id).isPresent()) {
            UserResponse dto = userMapper.mapToDto(userRepository.findById(id).get());
            dto.setRoles(getAuthority(userRepository.findById(id).get().getAuthorities()));
            return dto;
        } else
            throw new RecordNotFountException("This Record with id " + id + " Not Found");
    }

    /**
     * @param dto add new user
     */
    public void save(UserRequest dto) {
        this.save(userMapper.mapToEntity(dto));
    }

    /**
     * @param user add new user
     */
    public void save(AppUser user) {
        List<Authority> authorities = authorityService.findAll();
        Set<Authority> set = new HashSet<>();
        set.add(authorities.get(2));
        user.setAuthorities(set);
        user.setIsActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    /**
     * @param user,id add new user
     */
    public void save(AppUser user, int id) {
        user.setIsActive(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    /**
     * @param user update user
     */
    public void update(AppUser user) {

        user.setAuthorities(userRepository.findById(user.getId()).get().getAuthorities());
        user.setIsActive(1);
        user.setPassword((user.getPassword()));
        this.userRepository.save(user);
    }

    /**
     * @param dto,id update user by id
     */
    public void update(UserUpdate dto, byte id) {
        AppUser user = userMapper.mapToEntity(dto);
        user.setId(id);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setUsername(dto.getUsername());
        user.setAuthorities(userRepository.findById(id).get().getAuthorities());
        user.setIsActive(userRepository.findById(id).get().getIsActive());
        user.setImagePath(userRepository.findById(id).get().getImagePath());
        user.setPassword(userRepository.findById(id).get().getPassword());
        this.userRepository.save(user);
    }

    /**
     * @param username,password used to change password by user and check username and password
     */
    public void changePassword(String username, ChangePassword password) {
        AppUser user = this.findByUserName(username);
        if (passwordEncoder.matches(password.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password.getNewPassword()));
            userRepository.save(user);
        } else
            throw new InvalidPasswordException("invalid password");
    }

    /**
     * @param id,password used to change password by admin
     */
    public void changePassword(byte id, Pass password) {
        AppUser user = userRepository.findById(id).get();
        user.setPassword(passwordEncoder.encode(password.getNewPassword()));
        userRepository.save(user);
    }

    /**
     * @param username
     * @return user by username
     */
    public AppUser findByUserName(String username) {
        if (userRepository.findByUsername(username).isPresent())
            return userRepository.findByUsername(username).get();
        else
            throw new UsernameNotFoundException("This User " + username + " NotFound");
    }

    /**
     * @param username used to activated user
     */
    public void activeUser(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            AppUser user = findByUserName(username);
            user.setIsActive(1);
            userRepository.save(user);
        } else
            throw new UsernameNotFoundException("This user id Not Exist");
    }

    /**
     * @param username used to unActivated user
     */
    public void unActiveUser(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            AppUser user = findByUserName(username);
            user.setIsActive(0);
            userRepository.save(user);
        } else
            throw new UsernameNotFoundException("This user id Not Exist");
    }


}
