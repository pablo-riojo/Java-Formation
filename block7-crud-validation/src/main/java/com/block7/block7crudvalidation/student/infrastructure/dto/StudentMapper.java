package com.block7.block7crudvalidation.student.infrastructure.dto;

import com.block7.block7crudvalidation.student.domain.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper Instance = Mappers.getMapper(StudentMapper.class);

    StudentOutputDTO studentToStudentOutputDTO(Student student);
//    StudentSimpleOutputDTO studentToStudentSimpleOutputDTO(Student student);
    StudentSimpleRelationsOutputDTO studentToStudentSimpleRelationsOutputDTO(Student student);
    Student studentInputDTOtoStudent(StudentInputDTO studentInputDTO);
    StudentUpdateDTO studentToStudentUpdateDTO(Student student);
}
