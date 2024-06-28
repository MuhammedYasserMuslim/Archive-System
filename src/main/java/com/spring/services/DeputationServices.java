package com.spring.services;

import com.spring.model.entity.Deputation;
import com.spring.repository.DeputationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeputationServices {

    private final DeputationRepository deputationRepository;

    public List<Deputation> findAll() {
        return deputationRepository.findAll();}
}
