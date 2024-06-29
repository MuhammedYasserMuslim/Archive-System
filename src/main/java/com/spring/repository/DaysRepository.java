package com.spring.repository;

import com.spring.model.entity.Days;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysRepository extends JpaRepository<Days,Integer> {
}
