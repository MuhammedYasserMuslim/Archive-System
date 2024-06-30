package com.spring.repository;

import com.spring.model.entity.Deputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeputationRepository extends JpaRepository<Deputation, Integer> {

    String FIND_ALL = "SELECT * FROM deputation WHERE created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ";


    @Query(value = FIND_ALL, nativeQuery = true)
    List<Deputation> findByYear();
}
