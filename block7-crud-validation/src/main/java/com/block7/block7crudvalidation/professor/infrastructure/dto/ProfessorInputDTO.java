package com.block7.block7crudvalidation.professor.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonInputDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.professor.domain.Professor} entity
 */

@Data
public class ProfessorInputDTO implements Serializable {
    private PersonInputDTO person;
    private String comments;
    private String branch;
}
