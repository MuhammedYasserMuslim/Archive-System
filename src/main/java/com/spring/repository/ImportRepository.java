package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import com.spring.model.entity.Import;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportRepository extends JpaRepository<Import, Integer> {

    List<Import> findAllByOrderByIdDesc();
    List<Import> findBySummaryContaining(String summary);
    List<Import> findByArchiveFile(ArchiveFile archiveFile);
    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date >= CURRENT_DATE() and  expect_response_date <CURRENT_DATE()+ INTERVAL 3 DAY  AND response_id IS NULL" , nativeQuery = true)
    List<Import> findItIsTime();
    @Query(value = "SELECT count(*)  FROM archive.imports WHERE expect_response_date >= CURRENT_DATE() and  expect_response_date <CURRENT_DATE()+ INTERVAL 3 DAY  AND response_id IS NULL", nativeQuery = true)
    Long countItIsTime();
    @Query(value = "SELECT * FROM archive.imports WHERE expect_response_date >= (CURRENT_DATE() + INTERVAL 3 DAY) AND response_id IS NULL", nativeQuery = true)
    List<Import> findItIsNotTime();
    @Query(value = "SELECT count(*)  FROM archive.imports WHERE expect_response_date >= (CURRENT_DATE() + INTERVAL 3 DAY) AND response_id IS NULL", nativeQuery = true)
    Long countItIsNotTime();
    @Query(value = "SELECT import FROM Import import where import.expectResponseDate < current_date() and import.export is null")
    List<Import> findPassedDate();
    @Query(value = "SELECT count(import) FROM Import import where import.expectResponseDate < current_date() and import.export is null")
    Long countPassedDate();
    @Query(value = "SELECT import FROM Import import WHERE FUNCTION('DATE', import.incomeDate) = CURRENT_DATE ")
    List<Import> findByIncomeDate();
    @Query(value = "SELECT count(import) FROM Import import WHERE FUNCTION('DATE', import.incomeDate) = CURRENT_DATE")
    Long countCurrent();
    @Query(value = "SELECT import FROM Import import WHERE import.expectResponseDate IS NOT NULL ")
    List<Import> findImportantFile();
    @Query(value = "SELECT count(import) FROM Import import WHERE import.expectResponseDate IS NOT NULL")
    Long countImportantFile();


}
