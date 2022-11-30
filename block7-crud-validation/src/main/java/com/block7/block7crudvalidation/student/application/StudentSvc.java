package com.block7.block7crudvalidation.student.application;

import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.subject.domain.Subject;

import java.util.List;
import java.util.UUID;

public interface StudentSvc {
    List<Student> findAll();
    Student findById(UUID id);
    Student findByPersonId(UUID id);

    List<Student> findByProfessorId(UUID id);

    Student update(Student newStudent, UUID id);
    Professor addProfessor(Professor professor, UUID id);
    List<Subject> addSubjects(List<Subject> subjects, UUID id);
    void delete(UUID id);
    Student save(Student student);
}
