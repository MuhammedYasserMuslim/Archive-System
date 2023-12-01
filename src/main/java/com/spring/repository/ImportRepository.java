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
    @Query(value = "SELECT * FROM archive.imports where expect_response_date in( current_date() + INTERVAL 3 DAY, current_date() + INTERVAL 2 DAY, current_date() + INTERVAL 1 DAY, current_date()) and response_id is null",nativeQuery = true)
    List<Import> findItIsTime();



    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date > (CURRENT_DATE() + INTERVAL 3 DAY )",nativeQuery = true)
    List<Import>findItIsNotTime();

    @Query(value = "SELECT count(*) FROM archive.imports WHERE expect_response_date > (CURRENT_DATE() + INTERVAL 3 DAY )",nativeQuery = true)
    Long  countItIsNotTime();


    @Query(value = "SELECT count(*) FROM archive.imports where expect_response_date in( current_date() + INTERVAL 3 DAY, current_date() + INTERVAL 2 DAY, current_date() + INTERVAL 1 DAY, current_date() ) and response_id is null",nativeQuery = true)
    Long countItIsTime();
    @Query(value = "SELECT * FROM archive.imports where income_date = current_date()",nativeQuery = true)
    List<Import> findByIncomeDate();

    @Query(value = "SELECT * FROM archive.imports where expect_response_date < current_date() and response_id is null",nativeQuery = true)
    List<Import> findPassedDate();

    @Query(value = "SELECT count(*) FROM archive.imports where expect_response_date < current_date() and response_id is null;",nativeQuery = true)
    Long countPassedDate();

    @Query(value = "SELECT * FROM archive.imports where   expect_response_date  is not null ;",nativeQuery = true)
    List<Import> findImportantFile();

    @Query(value = "SELECT count(*) FROM archive.imports where  expect_response_date  is not null ;",nativeQuery = true)
    Long countImportantFile();

    @Query(value = "SELECT count(*) FROM archive.imports where income_date = current_date()",nativeQuery = true)
    Long countCurrent();
}
