package com.spring.services;

import com.spring.model.entity.Special;
import com.spring.model.entity.Subject;
import com.spring.repository.SpecialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SpecialServices {

    private final SpecialRepository specialRepository;
    private final SubjectServices subjectServices;

    public List<Special> findAll() {
        return specialRepository.findAll();
    }

    public List<Special> findBySubject(String summary) {
        List<Subject> subjects = subjectServices.findBySummaryContaining(summary);
        List<Special> specials = new ArrayList<>();
        for (Subject subject : subjects) {
            if (specialRepository.findById(subject.getSpecial().getId()).isPresent())
                specials.add(subject.getSpecial());
        }
        return abstractList(specials);

    }


    private List<Special> abstractList(List<Special> list) {
        Set<Special> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        list.sort(Comparator.comparing(Special::getId));
        return list;
    }
}
