package com.spring.services;

import com.spring.exception.RecordNotFountException;
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

    public List<DeputationDto> findByYear() {
        return deputationMapper.mapToDto(deputationRepository.findByYear());
    }

    public Integer countByYear() {
        return deputationRepository.findByYear().size();
    }

    public List<DeputationDto> findAccepted(){
        return deputationMapper.mapToDto(deputationRepository.findAccepted());
    }

    public Integer countAccepted(){
        return deputationRepository.findAccepted().size();
    }

    public List<DeputationDto> findNotAccepted(){
        return deputationMapper.mapToDto(deputationRepository.findNotAccepted());
    }

    public Integer countNotAccepted(){
        return deputationRepository.findNotAccepted().size();
    }


    public Deputation findById(int id) {
        return deputationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public DeputationDto insert(DeputationDto deputationDto) {
        deputationDto.setNo(findByYear().isEmpty() ? 1 : findByYear().get(findByYear().size() - 1).getNo() + 1);
        deputationRepository.save(deputationMapper.mapToEntity(deputationDto));
        return deputationDto;
    }

    public void update(DeputationDto deputationDto, int id) {
        Deputation deputation = deputationMapper.mapToEntity(deputationDto);
        deputation.setId(id);
        deputation.setNo(findById(id).getNo());
        deputation.setCreatedBy(findById(id).getCreatedBy());
        deputation.setCreatedDate(findById(id).getCreatedDate());

        deputationRepository.save(deputation);
    }

    public void deleteById(Integer id) {
        if (deputationRepository.findById(id).isPresent())
            deputationRepository.deleteById(id);
        else
            throw new RecordNotFountException("Not Found");
    }
}
