package com.spring.repository;

import com.spring.model.entity.Export;
import com.spring.model.entity.Special;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SpecialRepository extends JpaRepository<Special, Integer> {
    /**
     * @return specials in current year
     */
    @Query(value = "select * from special where created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) >= 1 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END ))", nativeQuery = true)
    List<Special> findByYear();

    /**
     * @return specials in current year for pagination
     */
    @Query(value = "SELECT * FROM special WHERE created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) >= 1 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ", nativeQuery = true)
    Page<Special> findByYear(Pageable pageable);

    /**
     * @return special in archive file
     */
    @Query(value = "SELECT * FROM archive.special where archive_file_id = :id and created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END ))" , nativeQuery = true)
    List<Special> findByArchiveFile(Short id);


    @Query(value = "SELECT count(*)  FROM special WHERE created_date >= DATE_FORMAT(CONCAT(:year, '-07-01 00:00:00'), '%Y-07-01 00:00:00') - INTERVAL 1 YEAR AND created_date <= DATE_FORMAT(CONCAT(:year, '-06-30 23:59:59'), '%Y-06-30 23:59:59') and import_num is null" , nativeQuery = true)
    Integer findByYearDate(String year);
}

