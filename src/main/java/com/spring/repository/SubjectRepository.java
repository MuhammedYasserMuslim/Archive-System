package com.spring.repository;

import com.spring.model.entity.Subject;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {


    @Modifying
    @Transactional
    @Query(value = "delete from subjects where id = :id", nativeQuery = true)
    void deleteById(int id);
}

