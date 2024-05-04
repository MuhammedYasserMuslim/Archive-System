package com.spring.services;

import com.spring.model.dto.incomesigns.IncomingSignsDto;
import com.spring.model.dto.incomesigns.IncomingSignsDtoPost;
import com.spring.model.entity.IncomingSigns;
import com.spring.model.mapper.IncomingSignsMapper;
import com.spring.repository.IncomingSignsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomingSignsServices {

    private final IncomingSignsRepository incomingSignsRepository;
    private final IncomingSignsMapper incomingSignsMapper;
    private final BaseDataServices baseDataServices;

    /**
     * @return count Incoming signs
     */
    public Long count() {
        return incomingSignsRepository.count();
    }

    /**
     * @return all Incoming signs
     */
    public List<IncomingSignsDto> findAll() {
        return incomingSignsMapper.mapToDto(incomingSignsRepository.findAll());
    }

    /**
     * @param id to find Incoming signs by
     * @return Incoming signs by id
     */
    public IncomingSignsDto findById(int id) {
        IncomingSigns incomingSigns = incomingSignsRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
        return incomingSignsMapper.mapToDto(incomingSigns);
    }

    /**
     * @param page number of page in pagination
     * @return Incoming signs  for pagination
     */
    public IncomingSignsDto findAllPagination(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        return incomingSignsMapper.mapToDto(incomingSignsRepository.findAll(pageable).getContent().get(0));
    }

    /**
     * @param dto add new Incoming sign
     */

    public void insert(IncomingSignsDtoPost dto) {
        baseDataServices.editAutoIncrementIncomingSigns();
        incomingSignsRepository.save(incomingSignsMapper.mapToEntity(dto));
    }

    /**
     * @param signs take new values
     * @param id    chose Incoming sign to update
     */
    public void update(IncomingSigns signs, int id) {
        signs.setId(id);
        signs.setCreatedBy(getById(id).getCreatedBy());
        signs.setCreatedDate(getById(id).getCreatedDate());
        incomingSignsRepository.save(signs);
    }

    private IncomingSigns getById(int id) {
        return incomingSignsRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT Found"));
    }


}
