package com.spring.services;


import com.spring.model.dto.special.SubjectDto;
import com.spring.model.entity.Decision;
import com.spring.model.entity.Subject;
import com.spring.model.mapper.SubjectMapper;
import com.spring.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SubjectServices {

    private final SubjectRepository subjectRepository;
    private final DecisionService decisionService;
    private final SubjectMapper subjectMapper;


    public List<SubjectDto> findAll() {
        List<SubjectDto> dtos = new ArrayList<>();
        for (Subject subject : subjectRepository.findAll()) {
            dtos.add(subjectMapper.mapToDto(subject));
        }
        return dtos;
    }

    public List<Subject> findByDecision(String summary) {
        List<Decision> decisions = decisionService.findBySummary(summary);
        List<Subject> subjects = new ArrayList<>();
        for (Decision decision : decisions) {
            if (subjectRepository.findById(decision.getSubject().getId()).isPresent())
                subjects.add(decision.getSubject());
        }
        return abstractList(subjects);
    }

    private List<Subject> abstractList(List<Subject> list) {
        Set<Subject> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        list.sort(Comparator.comparing(Subject::getId));
        return list;
    }

    public void insert(Subject subject) {
        List<Decision> decisions = subject.getDecision();
        for (Decision decision : decisions) {
            decision.setSubject(subject);
        }
        subjectRepository.save(subject);
    }


    public void removeAll(List<Subject> subjects){
        subjectRepository.deleteAll(subjects);
    }

    public void removeById(int id){
        subjectRepository.deleteById(id);
    }



}
