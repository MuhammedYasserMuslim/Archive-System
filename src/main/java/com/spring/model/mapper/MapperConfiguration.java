package com.spring.model.mapper;

import com.spring.model.dto.archivefile.ArchiveFileDto;
import com.spring.model.dto.exports.ExportDto;
import com.spring.model.dto.exports.ExportDtoPost;
import com.spring.model.dto.imports.ImportDto;
import com.spring.model.dto.imports.ImportDtoPost;
import com.spring.model.dto.special.DecisionDto;
import com.spring.model.dto.special.SpecialDto;
import com.spring.model.dto.special.SpecialDtoPost;
import com.spring.model.dto.special.SubjectDto;
import com.spring.model.entity.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ExportMapper exportMapper() {
        return new ExportMapper() {
            @Override
            public ExportDto mapToDto(Export entity) {
                return null;
            }

            @Override
            public Export mapToEntity(ExportDtoPost dto) {
                return null;
            }
        };
    }

    @Bean
    public ImportMapper importMapper() {
        return new ImportMapper() {
            @Override
            public ImportDto mapToDto(Import entity) {
                return null;
            }

            @Override
            public Import mapToEntity(ImportDtoPost dto) {
                return null;
            }
        };
    }

    @Bean
    public ArchiveFileMapper archiveFileMapper() {
        return new ArchiveFileMapper() {
            @Override
            public ArchiveFileDto mapToDto(ArchiveFile entity) {
                return null;
            }

            @Override
            public ArchiveFile mapToEntity(ArchiveFileDto dto) {
                return null;
            }
        };
    }

    @Bean
    public DecisionMapper decisionMapper() {
        return new DecisionMapper() {
            @Override
            public DecisionDto mapToDto(Decision entity) {
                return null;
            }

            @Override
            public Decision mapToEntity(DecisionDto dto) {
                return null;
            }
        };
    }

    @Bean
    public SpecialMapper specialMapper() {
        return new SpecialMapper() {
            @Override
            public SpecialDto mapToDto(Special entity) {
                return null;
            }

            @Override
            public Special mapToEntity(SpecialDtoPost dto) {
                return null;
            }
        };
    }

    @Bean
    public SubjectMapper subjectMapper() {
        return new SubjectMapper() {
            @Override
            public SubjectDto mapToDto(Subject entity) {
                return null;
            }

            @Override
            public Subject mapToEntity(SubjectDto dto) {
                return null;
            }
        };
    }
}
