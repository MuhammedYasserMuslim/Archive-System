package com.spring.repository;

import com.spring.model.entity.ArchiveFile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArchiveFileRepository extends JpaRepository<ArchiveFile, Short> {


    @Query("SELECT archiveFile from ArchiveFile archiveFile where archiveFile.typeNumber in (1,2,3) order by archiveFile.typeNumber , archiveFile.num")
    List<ArchiveFile> findAll();


    /**
     * @return archiveFile by name
     * select * from archive_file where name like %:name%
     */
    List<ArchiveFile> findByNameContaining(String name);

    /**
     * @return archiveFile by TypeNumber and num
     * select * from archive_file where typeNumber = :typeNumber and num = :num
     */
    Optional<ArchiveFile> findByTypeNumberAndNum(Byte typeNumber, Short num);

    @Transactional
    @Modifying
    @Query("UPDATE ArchiveFile file set file.name = :name where file.id = :id")
    void updateName(String name, Short id);


    @Transactional
    @Modifying
    void deleteByTypeNumberAndNum(Byte typeNumber, Short num);


    @Modifying
    @Transactional
    @Query(value = "update archive_file set type_number = 0 ", nativeQuery = true)
    void closeArchiveFile();
}
