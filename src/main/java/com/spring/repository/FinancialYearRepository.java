package com.spring.repository;

import com.spring.model.entity.FinancialYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialYearRepository extends JpaRepository<FinancialYear, String> {
}
