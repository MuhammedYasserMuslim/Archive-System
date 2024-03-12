package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import com.spring.model.entity.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportRepository extends JpaRepository<Export, Integer> {


    @Query(value = "SELECT * FROM exports WHERE date >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 YEAR), '%Y-07-01')   AND date<= DATE_FORMAT(NOW(), '%Y-06-30')", nativeQuery = true)
    List<Export> findByYear();

    List<Export> findAllByOrderByIdDesc();

    List<Export> findBySummaryContainingOrderByIdDesc(String summary);

    List<Export> findByArchiveFile(ArchiveFile archiveFile);

    @Query(value = "SELECT export FROM Export export WHERE FUNCTION('DATE', export.date) = CURRENT_DATE")
    List<Export> findByDate();

    @Query(value = "SELECT count(export) FROM Export export WHERE FUNCTION('DATE', export.date) = CURRENT_DATE")
    Long countCurrent();
}
