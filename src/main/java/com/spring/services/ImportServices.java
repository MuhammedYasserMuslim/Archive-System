package com.spring.services;

import com.spring.model.dto.ImportDto;
import com.spring.model.entity.Import;
import com.spring.model.mapper.ImportMapper;
import com.spring.repository.ImportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportServices {

    private final ImportRepository importRepository;
    private final ImportMapper importMapper;

    public List<ImportDto> findAll(){
        List<Import>imports = importRepository.findAll();
        List<ImportDto> dtos = new ArrayList<>();

        for (int i = 0; i < imports.size(); i++) {
            dtos.add(importMapper.mapToDto(imports.get(i)));
        }
        return dtos;
    }
}
