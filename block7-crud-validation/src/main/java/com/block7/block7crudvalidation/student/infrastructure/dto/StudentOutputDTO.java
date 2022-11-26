package com.block7.block7crudvalidation.student.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorOutputDTO;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class StudentOutputDTO {
    private UUID id;
    PersonOutputDTO person;
    ProfessorOutputDTO professor;
    int weekHours;
    String comments;
    String branch;
    Date createdAt;
    Date updatedAt;
}
