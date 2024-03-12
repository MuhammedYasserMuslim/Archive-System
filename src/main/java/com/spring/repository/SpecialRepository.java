package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import com.spring.model.entity.Special;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialRepository extends JpaRepository<Special, Integer> {

    @Query(value = "SELECT * FROM special WHERE income_date >= DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 YEAR), '%Y-07-01')   AND income_date<= DATE_FORMAT(NOW(), '%Y-06-30')", nativeQuery = true)
    List<Special> findByYear();

    List<Special> findByArchiveFile(ArchiveFile archiveFile);

    List<Special> findAllByOrderByIdDesc();


}

