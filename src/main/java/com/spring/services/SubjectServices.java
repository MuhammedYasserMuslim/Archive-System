package com.spring.services;


import com.spring.model.entity.Subject;
import com.spring.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServices {

    private final SubjectRepository subjectRepository;

    public List<Subject> findAll(){
        return subjectRepository.findAll();
    }

  public   List<Subject> findBySummaryContaining(String summary){
        return subjectRepository.findBySummaryContaining(summary);
    }

}
