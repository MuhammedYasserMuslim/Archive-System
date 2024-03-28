package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import com.spring.model.entity.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportRepository extends JpaRepository<Import, Integer> {

    /**
     * @return imports in current year
     */
    @Query(value = "select * from imports where income_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) >= 1 AND MONTH(CURRENT_DATE()) <= 3 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END AS financial_year)) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) >= 1 AND MONTH(CURRENT_DATE()) <= 3 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END AS financial_year))" , nativeQuery = true)
    List<Import> findByYear();

    /**
     * @return imports in current year for pagination
     */
    @Query(value = "SELECT * FROM imports WHERE income_date >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 YEAR), '%Y-07-01')   AND income_date<= DATE_FORMAT(NOW(), '%Y-06-30') ", nativeQuery = true)
    Page<Import> findByYear(Pageable pageable);

    /**
     * @return imports in archive file
     */
    List<Import> findByArchiveFile(ArchiveFile archiveFile);

    /**
     * @return important imports file
     */
    @Query(value = "SELECT * FROM archive.imports where   expect_response_date  is not null and income_date >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 YEAR), '%Y-07-01')   AND income_date<= DATE_FORMAT(NOW(), '%Y-06-30')", nativeQuery = true)
    List<Import> findImportantFile();

    /**
     * @return imports time has come
     */
    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date >= CURRENT_DATE() and  expect_response_date <CURRENT_DATE()+ INTERVAL 3 DAY  AND response_id IS NULL and income_date >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 YEAR), '%Y-07-01')   AND income_date<= DATE_FORMAT(NOW(), '%Y-06-30')", nativeQuery = true)
    List<Import> findItIsTime();

    /**
     * @return imports time has not come
     */
    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date >= (CURRENT_DATE() + INTERVAL 3 DAY) AND response_id IS NULL and income_date >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 YEAR), '%Y-07-01')   AND income_date<= DATE_FORMAT(NOW(), '%Y-06-30')", nativeQuery = true)
    List<Import> findItIsNotTime();

    /**
     * @return imports time has passed
     */
    @Query(value = "SELECT * FROM archive.imports where expect_response_date < current_date() and response_id is null and income_date >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 YEAR), '%Y-07-01')   AND income_date<= DATE_FORMAT(NOW(), '%Y-06-30')", nativeQuery = true)
    List<Import> findPassedDate();

    /**
     * @return today imports
     */
    @Query(value = "SELECT  * FROM archive.imports WHERE income_date >= CURRENT_DATE() AND income_date < CURRENT_DATE() + INTERVAL 1 DAY", nativeQuery = true)
    List<Import> findByIncomeDate();

    /**
     * @return imports by year
     */
    @Query(value = "SELECT * FROM imports WHERE income_date >= DATE_FORMAT(CONCAT(:year, '-07-01'), '%Y-07-01') - INTERVAL 1 YEAR AND income_date <= DATE_FORMAT(CONCAT(:year, '-06-30'), '%Y-06-30')", nativeQuery = true)
    List<Import> findByYearDate(String year);


}
