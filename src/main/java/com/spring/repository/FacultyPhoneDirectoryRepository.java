package com.spring.repository;

import com.spring.model.entity.FacultyPhoneDirectory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping
public interface FacultyPhoneDirectoryRepository extends JpaRepository<FacultyPhoneDirectory, Integer> {

    List<FacultyPhoneDirectory> findAllByOrderBySerialAsc();
}
