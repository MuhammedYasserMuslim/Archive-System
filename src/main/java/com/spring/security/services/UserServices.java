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
    public List<UserResponse> findAllUsers() {
        List<AppUser> users = findAll();
        List<UserResponse> dtos = new ArrayList<>();
        for (AppUser user : users) {
            UserResponse dto = userMapper.mapToDto(user);
            dto.setRoles(getAuthority(user.getAuthorities()));
            dtos.add(dto);
        }
        return dtos;
    }
    public long count() {
        return userRepository.count();
    }

    /**
     * @return user authorities
     */
    private String getAuthority(Set<Authority> authorities) {
        List<Authority> list = new ArrayList<>(authorities);
        list.sort(Comparator.comparing(Authority::getId));
        for (Authority authority : list) {
            switch (authority.getName()) {
                case "ROLE_ADMIN" -> {
                    return "admin";
                }
                case "ROLE_MANAGER" -> {
                    return "manager";
                }
                case "ROLE_USER" -> {
                    return "user";
                }
            }
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
     * @param id find user by
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

    public UserResponse findUserByUserName(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            UserResponse dto = userMapper.mapToDto(userRepository.findByUsername(username).get());
            dto.setRoles(getAuthority(userRepository.findByUsername(username).get().getAuthorities()));
            return dto;
        } else
            throw new RecordNotFountException("This Record with username " + username + " Not Found");
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
     * @deprecated
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

        user.setAuthorities(getById(user.getId()).getAuthorities());
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
        user.setPhone(dto.getPhone());
        user.setAuthorities(getById(id).getAuthorities());
        user.setIsActive(getById(id).getIsActive());
        user.setImagePath(getById(id).getImagePath());
        user.setPassword(getById(id).getPassword());
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
        AppUser user = getById(id);
        user.setPassword(passwordEncoder.encode(password.getNewPassword()));
        userRepository.save(user);
    }

    /**
     * @param username find user by
     * @return user
     */
    public AppUser findByUserName(String username) {
            return userRepository.findByUsername(username).orElse(null);
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

    private AppUser getById(Byte id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }


}
