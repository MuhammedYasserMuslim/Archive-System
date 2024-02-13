package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import com.spring.model.entity.Special;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialRepository extends JpaRepository<Special, Integer> {

    List<Special> findByArchiveFile(ArchiveFile archiveFile);


}

