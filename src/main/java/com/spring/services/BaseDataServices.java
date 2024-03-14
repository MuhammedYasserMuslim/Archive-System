package com.spring.services;

import com.spring.repository.BaseDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseDataServices {

    private final BaseDataRepository baseDataRepository;

    /**
     * @return imagesPath
     * used to find image folder path
     */
    public String findBaseData() {
        return baseDataRepository.findBaseData();
    }

    public List<String> findYears() {
        return baseDataRepository.findYears();
    }
}
