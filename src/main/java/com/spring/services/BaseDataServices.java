package com.spring.services;

import com.spring.repository.BaseDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BaseDataServices {

    private final BaseDataRepository baseDataRepository;

    public String findBaseData(){
        return baseDataRepository.findBaseData();
    }
}
