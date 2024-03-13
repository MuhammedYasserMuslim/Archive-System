package com.spring.services;


import com.spring.repository.GeneralRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GeneralServices {

    private final GeneralRepository generalRepository;

    public List<String> findYears() {
        return generalRepository.findYears();
    }
}
