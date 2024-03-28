package com.spring.services;

import com.spring.repository.BaseDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

//    public List<String> findYears() {
//        return baseDataRepository.findYears();
//    }


    public List<String> findYears() {
        List<String> years = new ArrayList<>();
        for (String srt : baseDataRepository.findYears())
            years.add(srt.concat("-").concat(String.valueOf(Integer.parseInt(srt) - 1)));
        return years;
    }
}
