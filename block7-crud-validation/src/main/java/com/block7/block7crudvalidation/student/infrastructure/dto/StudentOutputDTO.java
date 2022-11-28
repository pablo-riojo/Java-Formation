package com.block7.block7crudvalidation.student.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorOutputDTO;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectSimpleOutputDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class StudentOutputDTO {
    private UUID id;
    PersonOutputDTO person;
    ProfessorOutputDTO professor;
    List<SubjectSimpleOutputDTO> subjects = new ArrayList<>();
    int weekHours;
    String comments;
    String branch;
    Date createdAt;
    Date updatedAt;
}
