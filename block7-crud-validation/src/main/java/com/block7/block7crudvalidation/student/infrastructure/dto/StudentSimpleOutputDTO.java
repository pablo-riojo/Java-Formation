package com.block7.block7crudvalidation.student.infrastructure.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class StudentSimpleOutputDTO {
    private UUID id;
    private int weekHours;
    private String comments;
    private String branch;
    private Date createdAt;
    private Date updatedAt;
}
