package com.spring.services;

import com.spring.repository.BaseDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseDataServices {

    private final BaseDataRepository baseDataRepository;

    /**
     * used to save image folder path
     */
    public String findBaseData() {
        return baseDataRepository.findBaseData();
    }
}
