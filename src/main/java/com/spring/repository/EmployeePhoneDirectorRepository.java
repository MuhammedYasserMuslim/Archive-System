package com.spring.repository;

import com.spring.model.entity.EmployeePhoneDirector;
import com.spring.model.entity.TeachingPhoneDirector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeePhoneDirectorRepository extends JpaRepository<EmployeePhoneDirector, Integer> {

    List<EmployeePhoneDirector> findAllByOrderBySerialAsc();

}
