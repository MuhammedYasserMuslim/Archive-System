package com.spring.repository;

import com.spring.model.entity.Export;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportRepository extends JpaRepository<Export, Integer> {

    /**
     * @return exports in current year
     */
    @Query(value = "SELECT * FROM exports WHERE created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ", nativeQuery = true)
    List<Export> findByYear();

    /**
     * @return exports in current year for pagination
     */
    @Query(value = "SELECT * FROM exports WHERE created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ", nativeQuery = true)
    Page<Export> findByYear(Pageable pageable);

    /**
     * @return exports in archive file
     */
    @Query(value = "SELECT * FROM archive.exports where archive_file_id = :id and created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and saved = 0", nativeQuery = true)
    List<Export> findByArchiveFile(Short id);

//    /**
//     * @return today exports
//     */
//    @Query(value = "SELECT  * FROM archive.exports WHERE date >= CURRENT_DATE() AND date < CURRENT_DATE() + INTERVAL 1 DAY ", nativeQuery = true)
//    List<Export> findByDate();

    /**
     * @return today exports
     */
    @Query(value = "SELECT  * FROM archive.exports WHERE created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and (recipient_name is null or recipient_name = '') or date >= CURRENT_DATE() AND date < CURRENT_DATE() + INTERVAL 1 DAY order by no desc ", nativeQuery = true)
    List<Export> findByDate();

    /**
     * @return exports by year
     */
    @Query(value = "SELECT * FROM exports WHERE created_date >= DATE_FORMAT(CONCAT(:year, '-07-01 00:00:00'), '%Y-07-01 00:00:00') - INTERVAL 1 YEAR AND created_date <= DATE_FORMAT(CONCAT(:year, '-06-30 23:59:59'), '%Y-06-30 23:59:59')", nativeQuery = true)
    List<Export> findByYearDate(String year);

}
