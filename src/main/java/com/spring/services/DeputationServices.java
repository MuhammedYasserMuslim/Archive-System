package com.spring.services;

import com.spring.model.dto.deputation.DeputationDto;
import com.spring.model.entity.Deputation;
import com.spring.model.mapper.DeputationMapper;
import com.spring.repository.DeputationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeputationServices {

    private final DeputationRepository deputationRepository;
    private final DeputationMapper deputationMapper;

    public List<DeputationDto> findAll() {
        return deputationMapper.mapToDto(deputationRepository.findAll());
    }

    public DeputationDto save(DeputationDto deputationDto) {
        deputationRepository.save( deputationMapper.mapToEntity(deputationDto));
        return deputationDto;
    }
}
