package com.block7.block7crudvalidation.student.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonInputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorInputDTO;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectInputDTO;
import lombok.Data;

import java.util.List;

@Data
public class StudentInputDTO {
    PersonInputDTO person;
    ProfessorInputDTO professor;
    List<SubjectInputDTO> subjects;
    int weekHours;
    String comments;
    String branch;
}
