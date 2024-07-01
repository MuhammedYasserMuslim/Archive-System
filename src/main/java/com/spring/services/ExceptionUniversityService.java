package com.spring.services;

import com.spring.exception.ConflictException;
import com.spring.model.entity.ExceptionUniversity;
import com.spring.repository.ExceptionUniversityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ExceptionUniversityService {


    private final ExceptionUniversityRepository exceptionUniversityRepository;


    public List<ExceptionUniversity> findAll() {
        return exceptionUniversityRepository.findAll();
    }

    public ExceptionUniversity insert(ExceptionUniversity exceptionUniversity) {
        return exceptionUniversityRepository.save(exceptionUniversity);
    }

    public void deleteById(String id) {
        exceptionUniversityRepository.deleteById(id);
    }
}
