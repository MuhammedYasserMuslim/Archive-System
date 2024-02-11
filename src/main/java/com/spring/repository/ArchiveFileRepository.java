package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchiveFileRepository extends JpaRepository<ArchiveFile,Short> {
    List<ArchiveFile> findByTypeNumber(Byte typeNumber);
    List<ArchiveFile> findByNameContaining(String name);
    Optional<ArchiveFile> findByTypeNumberAndNum(Byte typeNumber, Short num);




}
