package com.block7.block7crudvalidation.professor.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentSimpleRelationsOutputDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.professor.domain.Professor} entity
 */

@Data
public class ProfessorOutputDTO extends ProfessorSimpleOutputDTO implements Serializable {
    private PersonOutputDTO person;
    private List<StudentSimpleRelationsOutputDTO> students;
}
