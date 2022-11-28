package com.block7.block7crudvalidation.subject.application.utils;

import com.block7.block7crudvalidation.subject.domain.Subject;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectMapper;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectUpdateDTO;

import java.util.Objects;

public class SubjectCheckings {
    public static Boolean isNewSubjectEqual(Subject newSubject, Subject subject) {
        SubjectUpdateDTO newSubjectUpdateDTO = SubjectMapper.Instance.subjectToSubjectUpdateDTO(newSubject);
        SubjectUpdateDTO subjectUpdateDTO = SubjectMapper.Instance.subjectToSubjectUpdateDTO(subject);

        return Objects.equals(newSubjectUpdateDTO, subjectUpdateDTO);
    }
}
