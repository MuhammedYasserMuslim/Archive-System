package com.spring.services;

import com.spring.model.dto.incomesigns.IncomingSignsDto;
import com.spring.model.entity.IncomingSigns;
import com.spring.model.mapper.IncomingSignsMapper;
import com.spring.repository.IncomingSignsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomingSignsServices {

    private final IncomingSignsRepository incomingSignsMapperRepository;
    private final IncomingSignsMapper incomingSignsMapper;
    private final BaseDataServices baseDataServices;


    public Long count() {
        return incomingSignsMapperRepository.count();
    }

    public List<IncomingSignsDto> findAll() {
        return incomingSignsMapper.mapToDto(incomingSignsMapperRepository.findAll());
    }

    public IncomingSignsDto findById(int id) {
        IncomingSigns incomingSigns = incomingSignsMapperRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return incomingSignsMapper.mapToDto(incomingSigns);
    }

    public IncomingSignsDto findAllPagination(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        return incomingSignsMapper.mapToDto(incomingSignsMapperRepository.findAll(pageable).getContent().get(0));
    }


    public void insert(IncomingSignsDto incomingSignsDto) {
        baseDataServices.editAutoIncrementIncomingSigns();
        incomingSignsMapperRepository.save(incomingSignsMapper.mapToEntity(incomingSignsDto));
    }

    public void update(IncomingSignsDto incomingSignsDto, int id) {
        baseDataServices.editAutoIncrementIncomingSigns();
        incomingSignsDto.setId(id);
        incomingSignsMapperRepository.save(incomingSignsMapper.mapToEntity(incomingSignsDto));
    }


}
