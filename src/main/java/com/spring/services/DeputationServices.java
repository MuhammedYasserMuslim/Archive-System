package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.deputation.DeputationDays;
import com.spring.model.dto.deputation.DeputationDto;
import com.spring.model.dto.deputation.DeputationPost;
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
        return findDistinctDeputation().stream().filter(dto -> dto.getDeputationDays().stream().allMatch(days -> days.getId() != dayOfWeek())).toList();
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
                mergedMap.put(days.getName(), new DeputationDays(days.getId(), days.getName(), new ArrayList<>(days.getDeputationDays())));
            }
        }
        return new ArrayList<>(mergedMap.values());
    }

    public List<DeputationDto> findDistinctDeputation() {
        List<DeputationDto> dtos = deputationMapper.mapToDto(deputationRepository.findCurrentDeputation());
        Map<String, DeputationDto> mergedMap = new HashMap<>();
        for (DeputationDto dto : dtos) {
            if (mergedMap.containsKey(dto.getName())) {
                List<Days> existingDays = mergedMap.get(dto.getName()).getDeputationDays();
                existingDays.addAll(dto.getDeputationDays());
            } else {
                mergedMap.put(dto.getName(),
                        new DeputationDto(dto.getId(), dto.getNo(), dto.getDegree(), dto.getName(), dto.getDepartment(), dto.getDeputationUniversity(), dto.getDeputationPeriod(), new ArrayList<>(dto.getDeputationDays()), dto.getDepartmentRecordNum(), dto.getDepartmentDate(), dto.getDepartmentAccept(), dto.getFacultyRecordNum(), dto.getFacultyDate(), dto.getFacultyAccept(), dto.getUniversityRecordNum(), dto.getUniversityDate(), dto.getUniversityAccept(), dto.getNotes()));
            }
        }
        return new ArrayList<>(mergedMap.values());
    }

    public Deputation findById(int id) {
        return deputationRepository.findById(id).orElseThrow(() -> new RuntimeException("Not Found"));
    }

    public DeputationPost findByIdPost(int id){
        return mapToDto(findById(id));
    }
    public void insert(DeputationPost deputationPost) {
        baseDataServices.editAutoIncrementDeputation();
        deputationPost.setNo(findByYear().isEmpty() ? 1 : findByYear().get(findByYear().size() - 1).getNo() + 1);
        deputationRepository.save(mapToEntity(deputationPost));
    }

    public void update(DeputationPost deputationPost, int id) {
        Deputation deputation = mapToEntity(deputationPost);
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
        for (ExceptionUniversity university : exceptionUniversityService.findAll()) {
            universities.add(university.getUniversity());
        }
        return universities;
    }

    private Deputation mapToEntity(DeputationPost dto) {
        List<Days> days = new ArrayList<>();
        for (Integer post : dto.getDeputationDaysIds()) {
            days.add(new Days(post));
        }
        return Deputation.builder()
                .no(dto.getNo())
                .degree(dto.getDegree())
                .name(dto.getName())
                .department(dto.getDepartment())
                .deputationUniversity(dto.getDeputationUniversity())
                .deputationPeriod(dto.getDeputationPeriod())
                .departmentRecordNum(dto.getDepartmentRecordNum())
                .departmentDate(dto.getDepartmentDate())
                .departmentAccept(dto.getDepartmentAccept())
                .facultyRecordNum(dto.getFacultyRecordNum())
                .facultyDate(dto.getFacultyDate())
                .facultyAccept(dto.getFacultyAccept())
                .universityRecordNum(dto.getUniversityRecordNum())
                .universityDate(dto.getUniversityDate())
                .universityAccept(dto.getUniversityAccept())
                .notes(dto.getNotes())
                .deputationDays(days)
                .build();
    }

    private DeputationPost mapToDto(Deputation entity) {
        List<Integer> days = new ArrayList<>();
        for (Days day : entity.getDeputationDays()) {
            days.add(day.getId());
        }
        return DeputationPost.builder()
                .id(entity.getId())
                .no(entity.getNo())
                .degree(entity.getDegree())
                .name(entity.getName())
                .department(entity.getDepartment())
                .deputationUniversity(entity.getDeputationUniversity())
                .deputationPeriod(entity.getDeputationPeriod())
                .departmentRecordNum(entity.getDepartmentRecordNum())
                .departmentDate(entity.getDepartmentDate())
                .departmentAccept(entity.getDepartmentAccept())
                .facultyRecordNum(entity.getFacultyRecordNum())
                .facultyDate(entity.getFacultyDate())
                .facultyAccept(entity.getFacultyAccept())
                .universityRecordNum(entity.getUniversityRecordNum())
                .universityDate(entity.getUniversityDate())
                .universityAccept(entity.getUniversityAccept())
                .notes(entity.getNotes())
                .deputationDaysIds(days)
                .build();
    }
}
