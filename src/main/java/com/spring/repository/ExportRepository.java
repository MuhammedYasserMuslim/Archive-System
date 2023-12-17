package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import com.spring.model.entity.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExportRepository extends JpaRepository<Export, Short> {


    List<Export> findBySummaryContaining(String summary);


    List<Export> findByArchiveFile(ArchiveFile archiveFile);

    @Query(value = "SELECT  * FROM archive.exports WHERE date >= CURRENT_DATE() AND date < CURRENT_DATE() + INTERVAL 1 DAY;", nativeQuery = true)
    List<Export> findByDate();

    @Query(value = "SELECT  count(*) FROM archive.exports WHERE date >= CURRENT_DATE() AND date < CURRENT_DATE() + INTERVAL 1 DAY;", nativeQuery = true)
    Long countCurrent();
}
