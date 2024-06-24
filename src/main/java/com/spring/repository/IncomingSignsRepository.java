package com.spring.repository;

import com.spring.model.entity.Export;
import com.spring.model.entity.IncomingSigns;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomingSignsRepository extends JpaRepository<IncomingSigns, Integer> {


    @Query(value = "SELECT * FROM incoming_signs WHERE created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ", nativeQuery = true)
    List<IncomingSigns> findByYear();


    @Query(value = "SELECT * FROM incoming_signs WHERE created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ", nativeQuery = true)
    Page<IncomingSigns> findByYear(Pageable pageable);
}
