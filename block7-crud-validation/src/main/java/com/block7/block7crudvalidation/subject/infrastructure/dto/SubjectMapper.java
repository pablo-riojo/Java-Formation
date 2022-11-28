package com.block7.block7crudvalidation.subject.infrastructure.dto;

import com.block7.block7crudvalidation.subject.domain.Subject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectMapper {
    SubjectMapper Instance = Mappers.getMapper(SubjectMapper.class);

    SubjectOutputDTO subjectToSubjectOutputDTO(Subject subject);
    Subject subjectInputDTOtoSubject(SubjectInputDTO subjectInputDTO);
    SubjectUpdateDTO subjectToSubjectUpdateDTO(Subject subject);
}
