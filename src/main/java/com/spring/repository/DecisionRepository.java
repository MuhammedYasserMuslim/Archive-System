package com.spring.repository;

import com.spring.model.entity.Decision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Integer> {
    /**
     *  @return  decision by summary
     * select * from decisions where summary like %:summary%
     */
    List<Decision> findBySummaryContaining(String summary);
}
