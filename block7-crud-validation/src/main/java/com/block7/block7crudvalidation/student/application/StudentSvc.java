package com.block7.block7crudvalidation.student.application;

import com.block7.block7crudvalidation.student.domain.Student;

import java.util.List;
import java.util.UUID;

public interface StudentSvc {
    List<Student> findAll();
    Student findById(UUID id);
    Student findByPersonId(UUID id);
//    Student findByProfessorId(UUID id);
    Student update(Student newStudent, UUID id);
    void delete(UUID id);
    Student save(Student student);
}
