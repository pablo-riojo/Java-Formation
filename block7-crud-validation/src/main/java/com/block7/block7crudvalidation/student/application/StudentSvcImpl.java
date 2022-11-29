package com.block7.block7crudvalidation.student.application;

import com.block7.block7crudvalidation.shared.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;
import com.block7.block7crudvalidation.student.application.utils.StudentCheckings;
import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.student.infrastructure.repository.StudentRepository;
import com.block7.block7crudvalidation.subject.domain.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentSvcImpl implements StudentSvc {
    @Autowired
    StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(UUID id) {
        return studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found"));
    }

    @Override
    public Student findByPersonId(UUID id) {
        return studentRepository.findByPersonId(id).orElseThrow(() -> new EntityNotFoundException("Student with person ID " + id + " not found"));
    }

    @Override
    public List<Student> findByProfessorId(UUID id) {
        return studentRepository.findByProfessorId(id).orElseThrow(() -> new EntityNotFoundException("Students with professor ID " + id + " not found"));
    }

    @Override
    public Student update(Student newStudent, UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found"));

        if (StudentCheckings.isNewStudentEqual(newStudent, student)) throw  new UnprocessableEntityException("Cannot update. Both students are equal");
        if (!StudentCheckings.isSameEmail(newStudent, student)) throw new UnprocessableEntityException("Cannot update. It must be same email: " + student.getPerson().getEmail());

        newStudent.setUpdateEffects(newStudent, id, student);

        return studentRepository.save(newStudent);
    }

    @Override
    public List<Subject> addSubjects(List<Subject> subjects, UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found"));

        subjects.forEach(student.getSubject()::add);
        studentRepository.save(student);

        return student.getSubject();
    }

    @Override
    public void delete(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found"));

        student.setDeleteEffects(student);

        studentRepository.save(student);

        studentRepository.deleteById(id);
    }

    @Override
    public Student save(Student student) {
        // TODO: Entity exception
        if (student.getProfessor() != null) student.setProfessorStudents(student);

        return studentRepository.save(student);
    }
}
