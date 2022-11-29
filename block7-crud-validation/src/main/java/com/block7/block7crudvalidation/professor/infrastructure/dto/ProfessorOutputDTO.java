package com.block7.block7crudvalidation.professor.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.professor.domain.Professor} entity
 */

@Data
public class ProfessorOutputDTO extends ProfessorSimpleOutputDTO implements Serializable {
    private PersonOutputDTO person;
}
