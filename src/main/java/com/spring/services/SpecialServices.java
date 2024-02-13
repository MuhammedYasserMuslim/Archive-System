package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.special.SpecialDto;
import com.spring.model.entity.Special;
import com.spring.model.entity.Subject;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.model.mapper.SpecialMapper;
import com.spring.repository.SpecialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SpecialServices {

    private final SpecialRepository specialRepository;
    private final SubjectServices subjectServices;
    private final SpecialMapper specialMapper;
    private final ArchiveFileMapper archiveFileMapper;
    private final ArchiveFileServices archiveFileServices;


    public int count() {
        return (int) specialRepository.count();
    }

    public List<SpecialDto> findAll() {
        List<SpecialDto> dtos = new ArrayList<>();
        for (Special special : specialRepository.findAll()) {
            dtos.add(specialMapper.mapToDto(special));
        }
        return dtos;
    }


    public SpecialDto findById(int id) {
        return specialMapper.mapToDto(specialRepository.findById(id)
                .orElseThrow(() -> new RecordNotFountException("Not Found")));
    }


    public List<SpecialDto> findBySubject(String summary) {
        List<Subject> subjects = subjectServices.findByDecision(summary);
        List<Special> specials = new ArrayList<>();
        List<SpecialDto> dtos = new ArrayList<>();
        for (Subject subject : subjects) {
            if (specialRepository.findById(subject.getSpecial().getId()).isPresent())
                specials.add(subject.getSpecial());
        }
        for (Special special : abstractList(specials)) {
            dtos.add(specialMapper.mapToDto(special));
        }
        return (dtos);

    }

    private List<Special> abstractList(List<Special> list) {
        Set<Special> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        list.sort(Comparator.comparing(Special::getId));
        return list;
    }

    public List<SpecialDto> findByArchiveFile(short id) {
        ArchiveFileDto dto = archiveFileServices.findById(id);
        List<Special> specials = specialRepository.findByArchiveFile(archiveFileMapper.mapToEntity(dto));
        List<SpecialDto> dtos = new ArrayList<>();
        for (Special special : specials) {
            dtos.add(specialMapper.mapToDto(special));
        }

        return dtos;
    }


//
//    public void insert(SpecialDtoPost dto) {
//        dto.setTypeNumber((byte) 3);
//        Special special = specialMapper.mapToEntity(dto);
//        special.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
//                (special.getArchiveFile().getTypeNumber(),
//                        special.getArchiveFile().getNum())));
//        List<Subject> subjects = dto.getSubjects();
//        for (Subject subject : subjects) {
//            subject.setSpecial(special);
//        }
//        subjectServices.insertAll(subjects);
//        specialRepository.save(special);
//    }
//
//    public void update(SpecialDtoPost dto , int id){
//        dto.setTypeNumber((byte) 3);
//        Special special = specialMapper.mapToEntity(dto);
//        special.setId(id);
//        special.setIncomeDate(specialRepository.findById(id).get().getIncomeDate());
//        special.setCreatedBy(specialRepository.findById(id).get().getCreatedBy());
//        special.setCreatedDate(specialRepository.findById(id).get().getCreatedDate());
//        special.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
//                (special.getArchiveFile().getTypeNumber(),
//                        special.getArchiveFile().getNum())));
//        subjectServices.removeAll(special.getSubject());
//        List<Subject> subjects = dto.getSubjects();
//        for (Subject subject : subjects) {
//            subject.setSpecial(special);
//        }
//        subjectServices.insertAll(subjects);
//        specialRepository.save(special);
//    }


}
