package com.spring.services;

import com.spring.repository.BaseDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
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

    public void editAutoIncrementExport() {
        baseDataRepository.editAutoIncrementExport();
        log.info("Execution Edit Auto Increment");

    }

    public void editAutoIncrementImport() {
        baseDataRepository.editAutoIncrementImport();
        log.info("Execution Edit Auto Increment");
    }

    public void editAutoIncrementSpecial() {
        baseDataRepository.editAutoIncrementSpecial();
        log.info("Execution Edit Auto Increment");
    }


    public void editAutoIncrementDeanDecisions() {
        baseDataRepository.editAutoIncrementDeanDecisions();
        log.info("Execution Edit Auto Increment");
    }
}
