package com.spring.repository;

import com.spring.model.entity.Deputation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeputationRepository extends JpaRepository<Deputation, Integer> {

    String FIND_ALL = "SELECT * FROM deputation WHERE created_date between( SELECT from_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) and ( SELECT to_date FROM archive.financial_year where financial_year = (SELECT CASE WHEN MONTH(CURRENT_DATE()) < 7 THEN CONCAT(YEAR(CURRENT_DATE()) - 1, '-', YEAR(CURRENT_DATE())) ELSE CONCAT(YEAR(CURRENT_DATE()), '-', YEAR(CURRENT_DATE()) + 1) END )) ";

    String FIND_ACCEPTED = FIND_ALL + "and university_accept = 1 and faculty_accept = 1 and department_accept = 1 ";

    String FIND_NOT_ACCEPTED = FIND_ALL + "and university_accept = 0 or faculty_accept = 0 or department_accept = 0 ";

    String FIND_CURRENT_DEPUTATION = FIND_ACCEPTED + "AND (deputation_period = 1 and month(current_date) in (9 ,10 ,11 , 12, 1)) or (deputation_period = 2 and month(current_date) in (2 ,3 , 4, 5 ,6))or (deputation_period = 3 and month(current_date) in (9, 10 ,11 , 12, 1 , 2 , 3 , 4 , 5 , 6)) ";

    @Query(value = FIND_ALL, nativeQuery = true)
    List<Deputation> findByYear();

    @Query(value = FIND_ACCEPTED, nativeQuery = true)
    List<Deputation> findAccepted();

    @Query(value = FIND_NOT_ACCEPTED , nativeQuery = true)
    List<Deputation> findNotAccepted();

    @Query(value = FIND_CURRENT_DEPUTATION, nativeQuery = true)
    List<Deputation> findCurrentDeputation();


}
