package com.spring.security.services;

import com.spring.security.model.entity.AppUser;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServices  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder ;

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public void save(AppUser user) {
        user.setIsActive(0);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }

    public AppUser findByUserName(String username){
        return userRepository.findByUsername(username).get();
    }


}
