package com.block7.block7crudvalidation.subject.infrastructure.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.subject.domain.Subject} entity
 */
@Data
public class SubjectUpdateDTO implements Serializable {
    private String name;
    private String comments;
    private Date initialDate;
    private Date finishDate;
}