package com.spring.repository;

import com.spring.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {


    List<Subject> findBySummaryContaining(String summary);

}

