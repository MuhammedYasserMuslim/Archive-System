package com.spring.security.services;

import com.spring.security.entity.AppUser;
import com.spring.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServices  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder ;

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public void save(AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
    }


}
