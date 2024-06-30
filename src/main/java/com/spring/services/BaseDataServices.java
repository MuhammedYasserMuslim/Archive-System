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

    public String findImagePath() {
        return baseDataRepository.findImagesPath();
    }


    public List<String> findYears() {
        return baseDataRepository.findYears();
    }

    public void editAutoIncrementExport() {
        baseDataRepository.editAutoIncrementExport();
    }

    public void editAutoIncrementImport() {
        baseDataRepository.editAutoIncrementImport();
    }

    public void editAutoIncrementSpecial() {
        baseDataRepository.editAutoIncrementSpecial();
    }


    public void editAutoIncrementDeanDecisions() {
        baseDataRepository.editAutoIncrementDeanDecisions();
    }

    public void editAutoIncrementIncomingSigns() {
        baseDataRepository.editAutoIncrementIncomingSigns();
    }

    public void editAutoIncrementDeputation() {
        baseDataRepository.editAutoIncrementDeputation();
    }
}
