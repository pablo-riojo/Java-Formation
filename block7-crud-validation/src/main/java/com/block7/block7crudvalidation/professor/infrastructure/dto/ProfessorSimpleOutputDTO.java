package com.block7.block7crudvalidation.professor.infrastructure.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ProfessorSimpleOutputDTO {
    private UUID id;
    private String comments;
    private String branch;
    private Date createdAt;
    private Date updatedAt;
}
