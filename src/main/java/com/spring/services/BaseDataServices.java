package com.spring.services;

import com.spring.repository.BaseDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
@Aspect
public class BaseDataServices {

    private final BaseDataRepository baseDataRepository;

    /**
     * @return imagesPath
     * used to find image folder path
     */

    public String findImagePath() {
        return baseDataRepository.findImagesPath();
    }

    public String findBackupPath() {
        return baseDataRepository.findBackupPath();
    }

    public List<String> findYears() {
        return baseDataRepository.findYears();
    }

    @Before("execution(public * com.spring.services.ExportServices.insert(..))")
    public void editAutoIncrementExport() {
        baseDataRepository.editAutoIncrementExport();
    }
    @Before("execution(public * com.spring.services.ExportServices.addUrgent(..))")
    public void editAutoIncrementExportUrgent() {
        baseDataRepository.editAutoIncrementExport();
    }

    @Before("execution(public * com.spring.services.ImportServices.insert(..))")
    public void editAutoIncrementImport() {
        baseDataRepository.editAutoIncrementImport();
    }

    @Before("execution(public * com.spring.services.SpecialServices.insert(..))")
    public void editAutoIncrementSpecial() {
        baseDataRepository.editAutoIncrementSpecial();
    }

    @Before("execution(public * com.spring.services.DeanDecisionsServices.insert(..))")
    public void editAutoIncrementDeanDecisions() {
        baseDataRepository.editAutoIncrementDeanDecisions();
    }

    @Before("execution(public * com.spring.services.IncomingSignsServices.insert(..))")
    public void editAutoIncrementIncomingSigns() {
        baseDataRepository.editAutoIncrementIncomingSigns();
    }

    @Before("execution(public * com.spring.services.DeputationServices.insert(..))")
    public void editAutoIncrementDeputation() {
        baseDataRepository.editAutoIncrementDeputation();
    }
}
