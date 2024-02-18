package com.spring.repository;

import com.spring.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    Optional<Image> findByName(String name);

    void deleteByImagePath(String imagePath);

    Optional<Image> findByImagePath(String imagePath);
}
