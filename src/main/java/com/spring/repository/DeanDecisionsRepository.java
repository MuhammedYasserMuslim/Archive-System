package com.spring.repository;

import com.spring.model.entity.DeanDecisions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeanDecisionsRepository extends JpaRepository<DeanDecisions, Integer> {

    @Query(value = "select decision from DeanDecisions decision where Year(decision.createdDate) = Year(current_date) order by decision.id desc ")
    List<DeanDecisions> findByYear();

    @Query(value = "select decision from DeanDecisions decision where Year(decision.createdDate) = Year(current_date)")
    Page<DeanDecisions> findByYear(Pageable pageable);
}
