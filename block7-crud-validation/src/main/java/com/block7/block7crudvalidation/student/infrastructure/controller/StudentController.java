package com.block7.block7crudvalidation.student.infrastructure.controller;

import com.block7.block7crudvalidation.student.application.StudentSvc;
import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentInputDTO;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentMapper;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentOutputDTO;
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
    public StudentOutputDTO getStudentById(@PathVariable UUID id) {
        Student student = studentSvc.findById(id);

        StudentOutputDTO response = StudentMapper.Instance.studentToStudentOutputDTO(student);

        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentOutputDTO createStudent(@RequestBody StudentInputDTO studentInput) {
        Student student = StudentMapper.Instance.studentInputDTOtoStudent(studentInput);

        StudentOutputDTO response = StudentMapper.Instance.studentToStudentOutputDTO(studentSvc.save(student));

        return response;
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
