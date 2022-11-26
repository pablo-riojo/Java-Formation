package com.block7.block7crudvalidation.student.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonInputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorInputDTO;
import lombok.Data;

@Data
public class StudentInputDTO {
    PersonInputDTO person;
    ProfessorInputDTO professor;
    int weekHours;
    String comments;
    String branch;
}
