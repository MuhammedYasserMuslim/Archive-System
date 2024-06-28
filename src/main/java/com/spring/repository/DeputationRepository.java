package com.spring.repository;

import com.spring.model.entity.Deputation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeputationRepository extends JpaRepository<Deputation, Long> {
}
