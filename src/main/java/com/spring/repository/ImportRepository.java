package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import com.spring.model.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportRepository extends JpaRepository<Import,Short> {
    List<Import> findBySummaryContaining(String summary);
    List<Import> findByArchiveFile(ArchiveFile archiveFile);
    @Query(value = "SELECT * FROM archive.imports where income_date = current_date()",nativeQuery = true)
    List<Import> findByIncomeDate();

    @Query(value = "SELECT count(*) FROM archive.imports where income_date = current_date()",nativeQuery = true)
    Long countCurrent();
}
