package com.block7.block7crudvalidation.student.infrastructure.dto;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonSimpleOutputDTO;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectSimpleOutputDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class StudentSimpleOutputDTO {
    private UUID id;
    private PersonSimpleOutputDTO person;
    private List<SubjectSimpleOutputDTO> subjects;
    private int weekHours;
    private String comments;
    private String branch;
    private Date createdAt;
    private Date updatedAt;

}
