package com.spring.repository;

import com.spring.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
