package com.spring.repository;

import com.spring.model.entity.IncomingSigns;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomingSignsRepository extends JpaRepository<IncomingSigns, Integer> {
}
