package com.spring.repository;

import com.spring.model.entity.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExportRepository extends JpaRepository<Export, Short> {
}
