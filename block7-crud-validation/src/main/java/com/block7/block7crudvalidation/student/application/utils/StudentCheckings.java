package com.block7.block7crudvalidation.student.application.utils;

import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentMapper;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentUpdateDTO;

import java.util.Objects;

public class StudentCheckings {
    public static Boolean isNewStudentEqual(Student newStudent, Student student) {
        StudentUpdateDTO newStudentUpdateDTO = StudentMapper.Instance.studentToStudentUpdateDTO(newStudent);
        StudentUpdateDTO studentUpdateDTO = StudentMapper.Instance.studentToStudentUpdateDTO(student);

        return Objects.equals(newStudentUpdateDTO, studentUpdateDTO);
    }

    public static Boolean isSameEmail(Student newStudent, Student student) {
        return Objects.equals(newStudent.getPerson().getEmail(), student.getPerson().getEmail());
    }
}
