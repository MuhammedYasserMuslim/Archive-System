package com.spring.repository;

import com.spring.model.entity.Image;
import com.spring.model.entity.Special;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
    /**
     * return image by name
     * select * from image where name = :name
     */
    Optional<Image> findByName(String name);

    /**
     * delete image by imagePath
     * delete from image where imagePath = :imagePath
     */
    void deleteByImagePath(String imagePath);

    /**
     * return image by imagePath
     * select * from image where imagePath = :imagePath
     */
    Optional<Image> findByImagePath(String imagePath);


    @Modifying
    @Transactional
    @Query(value = "update image set special_id = :specialId where import_id = :importId", nativeQuery = true)
    void convertImageImport(int specialId, int importId);

    @Modifying
    @Transactional
    @Query(value = "update image set special_id = :specialId where export_id = :exportId", nativeQuery = true)
    void convertImageExport(int specialId, int exportId);


}
