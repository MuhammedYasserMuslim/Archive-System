package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.special.SpecialDto;
import com.spring.model.dto.special.SpecialDtoPost;
import com.spring.model.entity.Special;
import com.spring.model.entity.Subject;
import com.spring.model.mapper.ArchiveFileMapper;
import com.spring.model.mapper.SpecialMapper;
import com.spring.repository.SpecialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    /**
     * @return count specials in current year
     */
    public int count() {
        return specialRepository.findByYear().size();
    }

    /**
     * @return all specials in all years
     */
    public List<SpecialDto> findAll() {
        return specialMapper.mapListToDto(specialRepository.findAll());
    }

    /**
     * @param page number of page in pagination
     * @return specials in current year for pagination
     */
    public SpecialDto findAllPagination(int page) {
        Pageable pageable = PageRequest.of(page, 1);
        return specialMapper.mapToDto(specialRepository.findByYear(pageable).getContent().get(0));
    }


    /**
     * @return specials in current year
     */
    public List<SpecialDto> findByYear() {
        return reverseList(specialMapper.mapListToDto(specialRepository.findByYear()));
    }

    /**
     * @param id to find special by
     * @return specials by id
     */
    public SpecialDto findById(int id) {
        return specialMapper.mapToDto(specialRepository.findById(id)
                .orElseThrow(() -> new RecordNotFountException("Not Found")));
    }

    /**
     * @param summary find special by subject summary
     * @return specials by subject
     */
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

    /**
     * @param list sort specials and abstract list
     */
    private List<Special> abstractList(List<Special> list) {
        Set<Special> set = new HashSet<>(list);
        list.clear();
        list.addAll(set);
        list.sort(Comparator.comparing(Special::getId));
        return list;
    }

    /**
     * @param id to find archive file by id
     * @return specials in archive file
     */
    public List<SpecialDto> findByArchiveFile(short id) {
        ArchiveFileDto dto = archiveFileServices.findById(id);
        List<Special> specials = specialRepository.findByArchiveFile(archiveFileMapper.mapToEntity(dto));
        List<SpecialDto> dtos = new ArrayList<>();
        for (Special special : specials) {
            dtos.add(specialMapper.mapToDto(special));
        }

        return dtos;
    }

    /**
     * add new export file
     *
     * @param dto add new special file
     */
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

    /**
     * @param dto take new values
     * @param id  chose special file to update
     */
    public void update(SpecialDtoPost dto, int id) {
        Special sp = getById(id);
        dto.setId(id);
        dto.setTypeNumber((byte) 3);
        Special special = specialMapper.mapToEntity(dto);
        special.setNo(sp.getNo());
        special.setSender(dto.getSender());
        special.setSummary(dto.getSummary());
        special.setIncomeDate(dto.getIncomeDate());
        special.setCreatedBy(getById(id).getCreatedBy());
        special.setCreatedDate(getById(id).getCreatedDate());
        special.setArchiveFile(archiveFileMapper.mapToEntity(archiveFileServices.findByTypeNumberAndNum((byte) 3, special.getArchiveFile().getNum())));

        List<Subject> subjectList = getById(id).getSubject();
        for (int i = 0; i < subjectList.size(); i++) {
            getById(id).getSubject().get(i).setSpecial(null);
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

    private Special getById(int id) {
        return specialRepository.findById(id).orElseThrow(() -> new RecordNotFountException("Not Found"));
    }



    private List<SpecialDto> reverseList(List<SpecialDto> dtos) {
        Collections.reverse(dtos);
        return dtos;
    }
}
