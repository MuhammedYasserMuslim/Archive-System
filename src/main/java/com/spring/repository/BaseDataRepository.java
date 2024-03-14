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
    @Query(value = "SELECT DISTINCT CASE WHEN date >= DATE_FORMAT(NOW(), '%Y-07-01') - INTERVAL 1 YEAR AND date <= DATE_FORMAT(NOW(), '%Y-06-30')THEN YEAR(NOW())ELSE YEAR(date)END AS extracted_years FROM exports ORDER BY  YEAR(date) ASC", nativeQuery = true)
    List<String> findYears();

}
