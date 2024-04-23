package com.spring.services;

import com.spring.model.dto.incomesigns.IncomingSignsDto;
import com.spring.model.entity.IncomingSigns;
import com.spring.model.mapper.IncomingSignsMapper;
import com.spring.repository.IncomingSignsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomingSignsServices {

    private final IncomingSignsRepository incomingSignsMapperRepository;
    private final IncomingSignsMapper incomingSignsMapper;
    private final BaseDataServices baseDataServices;


    public List<IncomingSignsDto> findAll() {
        return incomingSignsMapper.mapToDto(incomingSignsMapperRepository.findAll());
    }

    public IncomingSignsDto findById(int id) {
        IncomingSigns incomingSigns = incomingSignsMapperRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return incomingSignsMapper.mapToDto(incomingSigns);
    }


    public void insert(IncomingSignsDto incomingSignsDto) {
        incomingSignsMapperRepository.save(incomingSignsMapper.mapToEntity(incomingSignsDto));
        baseDataServices.editAutoIncrementIncomingSigns();
    }

    public void update(IncomingSignsDto incomingSignsDto, int id) {
        incomingSignsDto.setId(id);
        incomingSignsMapperRepository.save(incomingSignsMapper.mapToEntity(incomingSignsDto));
        baseDataServices.editAutoIncrementIncomingSigns();
    }
}
