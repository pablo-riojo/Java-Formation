package com.block7.block7crudvalidation.professor.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.professor.domain.Professor} entity
 */

@Data
public class ProfessorOutputDTO implements Serializable {
    private UUID id;
    private PersonOutputDTO person;
    private String comments;
    private String branch;
    private Date createdAt;
    private Date updatedAt;
}
