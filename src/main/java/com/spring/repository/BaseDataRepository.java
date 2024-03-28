package com.spring.repository;

import com.spring.model.entity.BaseData;
import org.springframework.data.jpa.repository.JpaRepository;
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

}
