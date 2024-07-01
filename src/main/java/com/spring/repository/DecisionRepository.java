package com.spring.repository;

import com.spring.model.entity.Decision;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DecisionRepository extends JpaRepository<Decision, Integer> {
    /**
     *  @return  decision by summary
     * select * from decisions where summary like %:summary%
     */
    List<Decision> findBySummaryContaining(String summary);


    @Modifying
    @Transactional
    @Query(value = "delete from decisions where subject_id = :id", nativeQuery = true)
    void deleteBySubjectId( Integer id);
}
