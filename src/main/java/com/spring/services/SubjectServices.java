package com.spring.services;


import com.spring.model.dto.special.SubjectDto;
import com.spring.model.entity.Subject;
import com.spring.model.mapper.SubjectMapper;
import com.spring.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServices {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public List<SubjectDto> findAll() {
        List<SubjectDto> dtos = new ArrayList<>();
        for (Subject subject: subjectRepository.findAll()) {
            dtos.add(subjectMapper.mapToDto(subject));
        }
        return dtos;
    }



    public void insert(Subject subject) {
        subjectRepository.save(subject);
    }

    public void insertAll(List<Subject> subject) {
        subjectRepository.saveAll(subject);
    }

    public void removeAll(List<Subject> subjects){
        subjectRepository.deleteAll(subjects);
    }


}
