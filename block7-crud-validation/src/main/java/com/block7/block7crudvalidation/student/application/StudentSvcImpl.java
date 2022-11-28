package com.block7.block7crudvalidation.student.application;

import com.block7.block7crudvalidation.shared.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;
import com.block7.block7crudvalidation.student.application.utils.StudentCheckings;
import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.student.infrastructure.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

//    @Override
//    public Student findByProfessorId(UUID id) {
//        return studentRepository.findByProfessorId(id).orElseThrow(() -> new EntityNotFoundException("Student with professor ID " + id + " not found"));
//    }

    @Override
    public Student update(Student newStudent, UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found"));

        if (StudentCheckings.isNewStudentEqual(newStudent, student)) throw  new UnprocessableEntityException("Cannot update. Both students are equal");
        if (!StudentCheckings.isSameEmail(newStudent, student)) throw new UnprocessableEntityException("Cannot update. It must be same email: " + student.getPerson().getEmail());


        // TODO: Student update effects
        newStudent.setId(id);
        newStudent.getPerson().setId(student.getPerson().getId());
        newStudent.getProfessor().setId(student.getProfessor().getId());
        newStudent.getProfessor().getPerson().setId(student.getProfessor().getPerson().getId());
        newStudent.setCreatedAt(student.getCreatedAt());
        newStudent.getPerson().setCreatedAt(student.getPerson().getCreatedAt());
//        newStudent.getPerson().setUpdatedAt(new Date());
        newStudent.setUpdatedAt(new Date());

        return studentRepository.save(newStudent);
    }

    @Override
    public void delete(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student save(Student student) {
        if (student.getProfessor() != null) {
            student.getProfessor().setStudents(List.of(student));

            student.getProfessor().getPerson().setIsProfessor(true);
        }

        return studentRepository.save(student);
    }
}
