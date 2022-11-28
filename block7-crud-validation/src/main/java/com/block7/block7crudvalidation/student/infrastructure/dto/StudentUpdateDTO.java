package com.block7.block7crudvalidation.student.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonUpdateDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorUpdateDTO;
import lombok.Data;

@Data
public class StudentUpdateDTO {
    PersonUpdateDTO person;
    ProfessorUpdateDTO professor;
    int weekHours;
    String comments;
    String branch;
}
