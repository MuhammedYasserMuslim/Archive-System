package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import com.spring.model.entity.Export;
import com.spring.model.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportRepository extends JpaRepository<Import,Short> {
    List<Import> findAllByOrderByIdDesc();
    List<Import> findBySummaryContaining(String summary);
    List<Import> findByArchiveFile(ArchiveFile archiveFile);

    //حان موعدها
    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date >= CURRENT_DATE() and  expect_response_date <CURRENT_DATE()+ INTERVAL 3 DAY  AND response_id IS NULL",nativeQuery = true)
    List<Import> findItIsTime();
    @Query(value = "SELECT count(*)  FROM archive.imports WHERE expect_response_date >= CURRENT_DATE() and  expect_response_date <CURRENT_DATE()+ INTERVAL 3 DAY  AND response_id IS NULL",nativeQuery = true)
    Long countItIsTime();
    //ذهب موعدها
    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date >= (CURRENT_DATE() + INTERVAL 3 DAY) AND response_id IS NULL",nativeQuery = true)
    List<Import>findItIsNotTime();
    @Query(value = "SELECT count(*)  FROM archive.imports WHERE expect_response_date >= (CURRENT_DATE() + INTERVAL 3 DAY) AND response_id IS NULL",nativeQuery = true)
    Long  countItIsNotTime();
    //ذهب موعدها
    @Query(value = "SELECT * FROM archive.imports where expect_response_date < current_date() and response_id is null",nativeQuery = true)
    List<Import> findPassedDate();
    @Query(value = "SELECT count(*) FROM archive.imports where expect_response_date < current_date() and response_id is null;",nativeQuery = true)
    Long countPassedDate();
    //وارد اليوم
    @Query(value = "SELECT  * FROM archive.imports WHERE income_date >= CURRENT_DATE() AND income_date < CURRENT_DATE() + INTERVAL 1 DAY",nativeQuery = true)
    List<Import> findByIncomeDate();
    @Query(value = "SELECT count(*)FROM archive.imports WHERE income_date >= CURRENT_DATE() AND income_date < CURRENT_DATE() + INTERVAL 1 DAY",nativeQuery = true)
    Long countCurrent();

    //الملفات الهامة
    @Query(value = "SELECT * FROM archive.imports where   expect_response_date  is not null ;",nativeQuery = true)
    List<Import> findImportantFile();
    @Query(value = "SELECT count(*) FROM archive.imports where  expect_response_date  is not null ;",nativeQuery = true)
    Long countImportantFile();


}
