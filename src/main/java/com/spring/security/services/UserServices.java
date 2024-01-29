package com.spring.security.services;

import com.spring.security.model.dto.UserDto;
import com.spring.security.model.entity.AppUser;
import com.spring.security.model.entity.Authority;
import com.spring.security.model.mapper.UserMapper;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServices  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder ;
    private final UserMapper userMapper;
    private final AuthorityService authorityService;

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public void save(UserDto dto) {
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

    public AppUser findByUserName(String username){
        return userRepository.findByUsername(username).get();
    }


}
