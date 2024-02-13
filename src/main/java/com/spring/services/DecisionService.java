package com.spring.services;

import com.spring.model.entity.Decision;
import com.spring.repository.DecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DecisionService {
    private final DecisionRepository decisionRepository;


    public List<Decision> findAll(){
        return decisionRepository.findAll();
    }


    public List<Decision> findBySummary(String summary){
        return decisionRepository.findBySummaryContaining(summary);
    }

}
