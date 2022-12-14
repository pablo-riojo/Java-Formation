package com.block7.block7crudvalidation.subject.infrastructure.dto;

import com.block7.block7crudvalidation.student.infrastructure.dto.StudentOutputDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.subject.domain.Subject} entity
 */
@Data
public class SubjectOutputDTO implements Serializable {
    private UUID id;
    private List<StudentOutputDTO> student;
    private String name;
    private String comments;
    private Date initialDate;
    private Date finishDate;
    private Date createdAt;
    private Date updatedAt;
}