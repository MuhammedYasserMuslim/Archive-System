package com.spring.services;

import com.spring.model.dto.special.SpecialDto;
import com.spring.model.entity.Special;
import com.spring.model.entity.Subject;
import com.spring.model.mapper.SpecialMapper;
import com.spring.repository.SpecialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SpecialServices {

    private final SpecialRepository specialRepository;
    private final SubjectServices subjectServices;
    private final SpecialMapper specialMapper;

    public List<SpecialDto> findAll() {
        List<SpecialDto> dtos = new ArrayList<>();
        for (Special special:specialRepository.findAll()) {
            dtos.add(specialMapper.mapToDto(special));
        }
        return dtos;
    }

    public List<SpecialDto> findBySubject(String summary) {
        List<Subject> subjects = subjectServices.findBySummaryContaining(summary);
        List<Special> specials = new ArrayList<>();
        List<SpecialDto> dtos = new ArrayList<>();
        for (Subject subject : subjects) {
            if (specialRepository.findById(subject.getSpecial().getId()).isPresent())
                specials.add(subject.getSpecial());
        }
        for (Special special:specials) {
            dtos.add(specialMapper.mapToDto(special));
        }
        return abstractList(dtos);

    }


    private List<SpecialDto> abstractList(List<SpecialDto> list) {
        Set<SpecialDto> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        list.sort(Comparator.comparing(SpecialDto::getId));
        return list;
    }

    public void insert(Special special) {
        specialRepository.save(special);
    }
}
