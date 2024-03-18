package com.spring.security.services;

import com.spring.security.model.entity.Authority;
import com.spring.security.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityService {

    private final AuthorityRepository authorityRepository;

    /**
     * @param authority add new authority
     */
    public void insert(Authority authority) {
        authorityRepository.save(authority);
    }

    /**
     * @return all authorities
     */
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }
}
