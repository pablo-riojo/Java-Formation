package com.block7.block7crudvalidation.subject.infrastructure.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.subject.domain.Subject} entity
 */
@Data
public class SubjectSimpleOutputDTO implements Serializable {
    private UUID id;
    private String name;
    private Date initialDate;
    private Date finishDate;
}