package com.spring.services;

import com.spring.model.dto.special.DecisionDto;
import com.spring.model.entity.Decision;
import com.spring.model.mapper.DecisionMapper;
import com.spring.repository.DecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DecisionService {

    private final DecisionRepository decisionRepository;
    private final DecisionMapper decisionMapper;

    /**
     * @return all decisions
     */
    public List<Decision> findAll() {
        return decisionRepository.findAll();
    }

    /**
     * @param summary
     * @return decisions by summary
     */
    public List<Decision> findBySummary(String summary) {
        return decisionRepository.findBySummaryContaining(summary);
    }

    /**
     * @param dto
     * add new decision
     */
    public void insert(DecisionDto dto) {
        decisionRepository.save(decisionMapper.mapToEntity(dto));
    }


}
