package com.block7.block7crudvalidation.professor.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonUpdateDTO;
import lombok.Data;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.professor.domain.Professor} entity
 */

@Data
public class ProfessorUpdateDTO {
    private PersonUpdateDTO person;
    private String comments;
    private String branch;
}
