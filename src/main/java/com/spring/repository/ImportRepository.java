package com.spring.repository;

import com.spring.model.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportRepository extends JpaRepository<Import,Short> {
    List<Import> findBySummaryContaining(String summary);
}
