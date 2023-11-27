package com.spring.repository;

import com.spring.model.entity.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportRepository extends JpaRepository<Export, Short> {

    List<Export> findBySummaryContaining(String summary);
}
