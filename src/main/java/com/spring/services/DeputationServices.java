package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.deputation.DeputationDays;
import com.spring.model.dto.deputation.DeputationDto;
import com.spring.model.entity.Days;
import com.spring.model.entity.Deputation;
import com.spring.model.entity.ExceptionUniversity;
import com.spring.model.mapper.DeputationMapper;
import com.spring.repository.DeputationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DeputationServices {

    private final DeputationRepository deputationRepository;
    private final DeputationMapper deputationMapper;
    private final BaseDataServices baseDataServices;
    private final ExceptionUniversityService exceptionUniversityService;

    public List<DeputationDto> findAll() {
        return deputationMapper.mapToDto(deputationRepository.findAll());
    }

    public List<DeputationDto> findByYear() {
        return deputationMapper.mapToDto(deputationRepository.findByYear());
    }


    public List<DeputationDto> findAccepted() {
        return deputationMapper.mapToDto(deputationRepository.findAccepted());
    }

    public List<DeputationDto> findNotAccepted() {
        return deputationMapper.mapToDto(deputationRepository.findNotAccepted());
    }

    public List<DeputationDto> findCurrentDeputation() {
        return deputationMapper.mapToDto(deputationRepository.findCurrentDeputation());
    }

    public List<DeputationDto> findTodayDeputation() {
        return findCurrentDeputation().stream().filter(dto -> dto.getDeputationDays().stream().anyMatch(days -> days.getId() == dayOfWeek())).toList();
    }

    public List<DeputationDto> findTodayIn() {
        return findCurrentDeputation().stream().filter(dto -> dto.getDeputationDays().stream().allMatch(days -> days.getId() != dayOfWeek())).toList();
    }

    public List<DeputationDto> findExceptionDeputation() {
        return findCurrentDeputation().stream().filter(dto -> universities().contains(dto.getDeputationUniversity())).toList();
    }

    public List<DeputationDays> findDeputationDays() {
        List<DeputationDays> deputationDays = deputationMapper.mapToDaysDto(deputationRepository.findCurrentDeputation());
        Map<String, DeputationDays> mergedMap = new HashMap<>();
        for (DeputationDays days : deputationDays) {
            if (mergedMap.containsKey(days.getName())) {
                List<Days> existingDays = mergedMap.get(days.getName()).getDeputationDays();
                existingDays.addAll(days.getDeputationDays());
            } else {
                mergedMap.put(days.getName(), new DeputationDays(days.getId(),days.getName(), new ArrayList<>(days.getDeputationDays())));
            }
        }
        return  new ArrayList<>(mergedMap.values());
    }


    public Deputation findById(int id) {
        return deputationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public DeputationDto insert(DeputationDto deputationDto) {
        baseDataServices.editAutoIncrementDeputation();
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

    private static int dayOfWeek() {
        int i = LocalDateTime.now().getDayOfWeek().getValue();
        return switch (i) {
            case 6 -> 1;
            case 7 -> 2;
            case 1 -> 3;
            case 2 -> 4;
            case 3 -> 5;
            case 4 -> 6;
            case 5 -> 7;
            default -> 0;
        };
    }

    private List<String> universities() {
        List<String> universities = new ArrayList<>();
        for (ExceptionUniversity university : exceptionUniversityService.findAll()){
            universities.add(university.getUniversity());
        }
        return universities;
    }
}
