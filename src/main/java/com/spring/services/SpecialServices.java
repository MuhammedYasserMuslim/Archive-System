package com.spring.services;

import com.spring.exception.RecordNotFountException;
import com.spring.model.dto.special.AllSpecialDto;
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
    public Integer count() {
        return (int) specialRepository.count();
    }

    /**
     * @return count specials in current year
     */
    public int countCurrent() {
        return specialRepository.findByYear().size();
    }

    /**
     * @return all specials in all years
     */
    public List<AllSpecialDto> findAll() {
        return specialMapper.mapAllToDto(specialRepository.findAll());
    }

    /**
     * @param page number of page in pagination
     * @return specials in current year for pagination
     */
    public SpecialDto findAllPagination(int page) {
        if (!specialRepository.findByYear().isEmpty()) {
            Pageable pageable = PageRequest.of(page, 1);
            return specialMapper.mapToDto(specialRepository.findByYear(pageable).getContent().get(0));
        }
        return null;
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
        return specialMapper.mapListToDto(specialRepository.findByArchiveFile(id));
    }

    /**
     * add new special file
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
        if (!(dto.getSubjects() == null)) {
            List<Subject> subjects = dto.getSubjects();
            for (int i = 0; i < subjects.size(); i++) {
                Subject subject = subjects.get(i);
                subject.setNum(i + 1);
                subject.setSpecial(special);
                subjectServices.insert(subject);
            }
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

        for (Subject subject : specialRepository.findById(id).get().getSubject()) {
            subjectServices.deleteById(subject.getId());
        }

        if (dto.getSubjects()==null ) {
            specialRepository.save(special);
        } else {
            for (int i = 0; i < dto.getSubjects().size(); i++) {
                dto.getSubjects().get(i).setNum(i + 1);
                dto.getSubjects().get(i).setHead(dto.getSubjects().get(i).getHead());
                dto.getSubjects().get(i).setSpecial(special);
                subjectServices.insert(dto.getSubjects().get(i));
            }
            specialRepository.save(special);
        }


    }

    /**
     * @return special by id
     */
    private Special getById(int id) {
        return specialRepository.findById(id).orElseThrow(() -> new RecordNotFountException("Not Found"));
    }

    /**
     * @param dtos to reverse list
     * @return specialDto
     */
    private List<SpecialDto> reverseList(List<SpecialDto> dtos) {
        Collections.reverse(dtos);
        return dtos;
    }

    public int findByYearDate(String year) {
        return specialRepository.findByYearDate(year);
    }
}
