package com.spring.repository;

import com.spring.model.entity.BaseData;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseDataRepository extends JpaRepository<BaseData, Byte> {
    /**
     * used to save image folder path
     * select images_path from base_data
     */
    @Query("SELECT base.imagesPath FROM BaseData base")
    String findBaseData();

    /**
     * @return years
     */
    @Query(value = "SELECT distinct CASE WHEN MONTH(date) >= 7 THEN CONCAT(YEAR(date), '-', YEAR(date) + 1) ELSE CONCAT(YEAR(date) - 1, '-', YEAR(date)) END  FROM exports; ", nativeQuery = true)
    List<String> findYears();


    @Modifying
    @Transactional
    @Query(value = " alter table exports auto_increment 1", nativeQuery = true)
    void editAutoIncrementExport();

    @Modifying
    @Transactional
    @Query(value = " alter table imports auto_increment 1", nativeQuery = true)
    void editAutoIncrementImport();

    @Modifying
    @Transactional
    @Query(value = " alter table special auto_increment 1", nativeQuery = true)
    void editAutoIncrementSpecial();

}
