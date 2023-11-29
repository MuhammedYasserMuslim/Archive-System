package com.spring.repository;

import com.spring.model.entity.Export;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExportRepository extends JpaRepository<Export, Short> {

    List<Export> findBySummaryContaining(String summary);

    @Query(value = "SELECT * FROM archive.exports where date = current_date()",nativeQuery = true)
    List<Export> findByDate();

    @Query(value = "SELECT count(*) FROM archive.exports where date = current_date()",nativeQuery = true)
    Long countCurrent();
}
