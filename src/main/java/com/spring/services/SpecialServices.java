package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.special.SpecialDto;
import com.spring.model.dto.special.SpecialDtoPost;
import com.spring.model.entity.Export;
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
        for (Special special : specialRepository.findAllByOrderByIdDesc()) {
            dtos.add(specialMapper.mapToDto(special));
        }
        return dtos;
    }

    public List<SpecialDto> findByYear() {
        List<SpecialDto> dtos = new ArrayList<>();
        for (Special special : specialRepository.findByYear()) {
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

    public void insert(SpecialDtoPost dto) {
        List<Special> specials = specialRepository.findByYear();
        dto.setTypeNumber((byte) 3);
        Special special = specialMapper.mapToEntity(dto);
        special.setNo(specials.isEmpty() ? 1 : specials.get(specials.size() - 1).getNo() + 1);
        special.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum
                (special.getArchiveFile().getTypeNumber(),
                        special.getArchiveFile().getNum())));
        specialRepository.save(special);
        List<Subject> subjects = dto.getSubjects();
        for (Subject subject : subjects) {
            subject.setSpecial(special);
            subjectServices.insert(subject);
        }
    }

    public void update(SpecialDtoPost dto, int id) {
        Special sp = specialRepository.findById(id).get();
        dto.setId(id);
        dto.setTypeNumber((byte) 3);
        Special special = specialMapper.mapToEntity(dto);
        special.setNo(sp.getNo());
        special.setSender(dto.getSender());
        special.setSummary(dto.getSummary());
        special.setIncomeDate(dto.getIncomeDate());
        special.setCreatedBy(specialRepository.findById(id).get().getCreatedBy());
        special.setCreatedDate(specialRepository.findById(id).get().getCreatedDate());
        special.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum((byte) 3, special.getArchiveFile().getNum())));


        List<Subject> subjectList = specialRepository.findById(id).get().getSubject();

        for (int i = 0; i < subjectList.size(); i++) {
            specialRepository.findById(id).get().getSubject().get(i).setSpecial(null);
        }


        specialRepository.save(special);
        List<Subject> subjects = dto.getSubjects();
        for (Subject subject : subjects) {
            subject.setSpecial(special);
            subjectServices.insert(subject);
        }

        for (Subject subject : subjectList) {
            subjectServices.removeById(subject.getId());
            System.out.println(subject.getId());
        }

    }
}
