package com.spring.services;


import com.spring.model.dto.deandecisions.DeanDecisionsDto;
import com.spring.model.dto.deandecisions.DeanDecisionsDtoPost;
import com.spring.model.entity.DeanDecisions;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.model.mapper.DeanDecisionsDtoMapper;
import com.spring.repository.DeanDecisionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeanDecisionsServices {

    private final DeanDecisionsRepository deanDecisionsRepository;

    private final DeanDecisionsDtoMapper deanDecisionsDtoMapper;

    private final ArchiveFileServices archiveFileServices;

    private final ArchiveFileMapper archiveFileMapper;

    private final BaseDataServices baseDataServices;


    public Long count(){
        return deanDecisionsRepository.count();
    }

    public Integer countCurrent(){
        return findByYear().size();
    }

    public List<DeanDecisionsDto> findAll() {
        return deanDecisionsDtoMapper.mapListToDto(deanDecisionsRepository.findAll());
    }

    public List<DeanDecisionsDto> findByYear() {
        return deanDecisionsDtoMapper.mapListToDto(deanDecisionsRepository.findByYear());
    }

    public DeanDecisionsDto findById(int id) {
        DeanDecisions deanDecisions = deanDecisionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return deanDecisionsDtoMapper.mapToDto(deanDecisions);
    }

    public DeanDecisionsDto findAllPaginationByYear(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        return deanDecisionsDtoMapper.mapToDto(deanDecisionsRepository.findByYear(pageable).getContent().get(0));
    }

    public void insert(DeanDecisionsDtoPost dto) {
        baseDataServices.editAutoIncrementDeanDecisions();
        dto.setTypeNumber((byte) 2);
        DeanDecisions decisions = deanDecisionsDtoMapper.mapToEntity(dto);
        List<DeanDecisions> deanDecisions = deanDecisionsRepository.findByYear();
        decisions.setNo(deanDecisions.isEmpty() ? 1 : deanDecisions.get(deanDecisions.size() - 1).getNo() + 1);
        decisions.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
                (decisions.getArchiveFile().getTypeNumber(),
                        decisions.getArchiveFile().getNum())));
        deanDecisionsRepository.save(decisions);
    }


    public void update(DeanDecisionsDtoPost dto, int id) {
        baseDataServices.editAutoIncrementDeanDecisions();
        dto.setId(id);
        DeanDecisions decisions = deanDecisionsDtoMapper.mapToEntity(dto);
        DeanDecisions dec = getById(id);
        dto.setTypeNumber((byte) 2);
        decisions.setId(dto.getId());
        decisions.setNo(dec.getNo());
        decisions.setSummary(dto.getSummary());
        decisions.setDate(dto.getDate());
        decisions.setCreatedBy(getById(dto.getId()).getCreatedBy());
        decisions.setCreatedDate(getById(dto.getId()).getCreatedDate());
        decisions.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum((byte) 2, decisions.getArchiveFile().getNum())));
        deanDecisionsRepository.save(decisions);

    }


    private DeanDecisions getById(int id) {
        return deanDecisionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

}
