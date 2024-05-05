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


    /**
     * @return count dean-decision
     */
    public Long count(){
        return deanDecisionsRepository.count();
    }

    /**
     * @return count dean-decision in current year
     */
    public Integer countCurrent(){
        return findByYear().size();
    }

    /**
     * @return all dean-decision in all years
     */
    public List<DeanDecisionsDto> findAll() {
        return deanDecisionsDtoMapper.mapListToDto(deanDecisionsRepository.findAll());
    }

    /**
     * @return dean-decision in current year
     */
    public List<DeanDecisionsDto> findByYear() {
        return deanDecisionsDtoMapper.mapListToDto(deanDecisionsRepository.findByYear());
    }

    /**
     * @param id to find export by
     * @return dean-decision by id
     */
    public DeanDecisionsDto findById(int id) {
        DeanDecisions deanDecisions = deanDecisionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return deanDecisionsDtoMapper.mapToDto(deanDecisions);
    }


    /**
     * @param page number of page in pagination
     * @return dean-decision in current year for pagination
     */
    public DeanDecisionsDto findAllPaginationByYear(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        return deanDecisionsDtoMapper.mapToDto(deanDecisionsRepository.findByYear(pageable).getContent().get(0));
    }

    /**
     * @param dto add new dean-decision file
     */
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

    /**
     * @param dto take new values
     * @param id  chose dean-decision file to update
     */
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
