package com.block7.block7crudvalidation.student.infrastructure.controller;

import com.block7.block7crudvalidation.person.infrastructure.dto.PersonMapper;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorInputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorMapper;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorSimpleOutputDTO;
import com.block7.block7crudvalidation.student.application.StudentSvc;
import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.student.infrastructure.dto.*;
import com.block7.block7crudvalidation.subject.domain.Subject;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectInputDTO;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectMapper;
import com.block7.block7crudvalidation.subject.infrastructure.dto.SubjectSimpleOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("UnnecessaryLocalVariable")
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentSvc studentSvc;

    @GetMapping("/all")
    public List<StudentOutputDTO> getAllStudents() {
        return studentSvc.findAll().stream().map(
                StudentMapper.Instance::studentToStudentOutputDTO
        ).toList();
    }

    @GetMapping("/{id}")
    public StudentOutputDTO getStudentById(@PathVariable UUID id, @RequestParam(name = "output", required = false) String output) {
//        if (output != null &&!output.isBlank() && output.equals("simple")) return getStudentSimpleById(id);

        Student student = studentSvc.findById(id);

        StudentOutputDTO response = StudentMapper.Instance.studentToStudentOutputDTO(student);

        if(student.getSubject() != null) {
            List<SubjectSimpleOutputDTO> subjects = student.getSubject().stream().map(SubjectMapper.Instance::subjectToSubjectSimpleOutputDTO).toList();
            response.setSubjects(subjects);
        }

        return response;
    }

    private StudentSimpleRelationsOutputDTO getStudentSimpleById(UUID id) {
        Student student = studentSvc.findById(id);

        StudentSimpleRelationsOutputDTO response = StudentMapper.Instance.studentToStudentSimpleRelationsOutputDTO(student);

        return response;
    }

    @GetMapping("/{id}/subjects")
    public List<SubjectSimpleOutputDTO> getSubjects(@PathVariable UUID id) {
        Student student = studentSvc.findById(id);

        return student.getSubject().stream().map(
                SubjectMapper.Instance::subjectToSubjectSimpleOutputDTO
        ).toList();
    }

    @GetMapping
    public List<StudentSimpleRelationsOutputDTO> getStudentsByProfessorId(@RequestParam(name = "professor") UUID id) {
        studentSvc.findByProfessorId(id).forEach(student -> PersonMapper.Instance.personToPersonSimpleOutputDTO(student.getPerson()));

        return studentSvc.findByProfessorId(id).stream().map(
                StudentMapper.Instance::studentToStudentSimpleRelationsOutputDTO
        ).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentOutputDTO createStudent(@RequestBody StudentInputDTO studentInput) {
        Student student = StudentMapper.Instance.studentInputDTOtoStudent(studentInput);

        if(studentInput.getSubject() != null) {
            List<Subject> subjects = studentInput.getSubject().stream().map(SubjectMapper.Instance::subjectInputDTOtoSubject).toList();
            student.setSubject(subjects);
        }

        StudentOutputDTO response = StudentMapper.Instance.studentToStudentOutputDTO(studentSvc.save(student));

        if (student.getSubject() != null) {
            List<SubjectSimpleOutputDTO> subjectOutput = student.getSubject().stream().map(SubjectMapper.Instance::subjectToSubjectSimpleOutputDTO).toList();
            response.setSubjects(subjectOutput);
        }

        return response;
    }

    @PatchMapping("/{id}/professor")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorSimpleOutputDTO addProfessor(@PathVariable UUID id, @RequestBody ProfessorInputDTO newProfessor) {
        Professor professor = ProfessorMapper.Instance.professorInputDTOToProfessor(newProfessor);

        Professor response = studentSvc.addProfessor(professor, id);

        return ProfessorMapper.Instance.professorToProfessorSimpleOutputDTO(response);
    }

    @PatchMapping("/{id}/subjects")
    @ResponseStatus(HttpStatus.CREATED)
    public List<SubjectSimpleOutputDTO> addSubjects(@PathVariable UUID id, @RequestBody List<SubjectInputDTO> newSubjects) {
        List<Subject> newSubjectsList = newSubjects.stream().map(SubjectMapper.Instance::subjectInputDTOtoSubject).toList();

        List<Subject> response = studentSvc.addSubjects(newSubjectsList, id);

        return response.stream().map(SubjectMapper.Instance::subjectToSubjectSimpleOutputDTO).toList();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentOutputDTO updateStudent(@RequestBody StudentInputDTO studentInput, @PathVariable UUID id) {
            Student newStudent = StudentMapper.Instance.studentInputDTOtoStudent(studentInput);

            StudentOutputDTO response = StudentMapper.Instance.studentToStudentOutputDTO(studentSvc.update(newStudent, id));
            response.setUpdatedAt(new Date());

            return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable UUID id) {
        studentSvc.delete(id);
    }
}
