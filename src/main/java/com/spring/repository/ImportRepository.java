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
    @Query(value = "SELECT * FROM imports WHERE income_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ", nativeQuery = true)
    List<Import> findByYear();

    /**
     * @return imports in current year for pagination
     */
    @Query(value = "SELECT * FROM imports WHERE income_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ", nativeQuery = true)
    Page<Import> findByYear(Pageable pageable);

    /**
     * @return imports in archive file
     */
    List<Import> findByArchiveFile(ArchiveFile archiveFile);

    /**
     * @return important imports file
     */
    @Query(value = "SELECT * FROM archive.imports where   expect_response_date  is not null and income_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END ))", nativeQuery = true)
    List<Import> findImportantFile();

    /**
     * @return imports time has come
     */
    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date >= CURRENT_DATE() and  expect_response_date <CURRENT_DATE()+ INTERVAL 3 DAY  AND response_id IS NULL and income_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END ))", nativeQuery = true)
    List<Import> findItIsTime();

    /**
     * @return imports time has not come
     */
    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date >= (CURRENT_DATE() + INTERVAL 3 DAY) AND response_id IS NULL and income_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END ))", nativeQuery = true)
    List<Import> findItIsNotTime();

    /**
     * @return imports time has passed
     */
    @Query(value = "SELECT * FROM archive.imports where expect_response_date < current_date() and response_id is null and income_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7  THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END ))", nativeQuery = true)
    List<Import> findPassedDate();

    /**
     * @return today imports
     */
    @Query(value = "SELECT  * FROM archive.imports WHERE income_date >= CURRENT_DATE() AND income_date < CURRENT_DATE() + INTERVAL 1 DAY", nativeQuery = true)
    List<Import> findByIncomeDate();

    /**
     * @return imports by year
     */
    @Query(value = "SELECT * FROM imports WHERE income_date >= DATE_FORMAT(CONCAT(:year, '-07-01 00:00:00'), '%Y-07-01 00:00:00') - INTERVAL 1 YEAR AND income_date <= DATE_FORMAT(CONCAT(:year, '-06-30 23:59:59'), '%Y-06-30 23:59:59')", nativeQuery = true)
    List<Import> findByYearDate(String year);


}
