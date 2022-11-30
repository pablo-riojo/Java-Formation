package com.block7.block7crudvalidation.professor.infrastructure.controller;

import com.block7.block7crudvalidation.professor.application.ProfessorSvc;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorInputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorMapper;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorOutputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorSimpleOutputDTO;
import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentInputDTO;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentMapper;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentSimpleRelationsOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("UnnecessaryLocalVariable")
@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    ProfessorSvc professorSvc;

    @GetMapping("/all")
    public List<ProfessorOutputDTO> getAllProfessors() {
        return professorSvc.findAll().stream().map(
                ProfessorMapper.Instance::professorToProfessorOutputDTO
        ).toList();
    }

    @GetMapping("/{id}")
    public ProfessorSimpleOutputDTO getProfessorById(@PathVariable UUID id, @RequestParam(name = "output", required = false) String output) {
        if (output != null &&!output.isBlank() && output.equals("simple")) return getProfessorSimpleById(id);

        Professor professor = professorSvc.findById(id);

        ProfessorOutputDTO response = ProfessorMapper.Instance.professorToProfessorOutputDTO(professor);

        if(professor.getStudents() != null) {
            List<StudentSimpleRelationsOutputDTO> students = professor.getStudents().stream().map(StudentMapper.Instance::studentToStudentSimpleRelationsOutputDTO).toList();
            response.setStudents(students);
        }

        return response;
    }

    public ProfessorSimpleOutputDTO getProfessorSimpleById(UUID id) {
            Professor professor = professorSvc.findById(id);

            ProfessorSimpleOutputDTO response = ProfessorMapper.Instance.professorToProfessorSimpleOutputDTO(professor);

            return response;
    }

    @GetMapping("/person/{id}")
    public ProfessorOutputDTO getProfessorByPersonId(@PathVariable UUID id) {
        Professor professor = professorSvc.findByPersonId(id);

        ProfessorOutputDTO response = ProfessorMapper.Instance.professorToProfessorOutputDTO(professor);

        return response;
    }

    @GetMapping("/{id}/students")
    public List<StudentSimpleRelationsOutputDTO> getStudents(@PathVariable UUID id) {
        return professorSvc.findStudents(id).stream().map(
                StudentMapper.Instance::studentToStudentSimpleRelationsOutputDTO
                ).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorOutputDTO createProfessor(@RequestBody ProfessorInputDTO professorInput) {
        Professor professor = ProfessorMapper.Instance.professorInputDTOToProfessor(professorInput);

        ProfessorOutputDTO response = ProfessorMapper.Instance.professorToProfessorOutputDTO(professorSvc.save(professor));

        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProfessorOutputDTO updateProfessor(@PathVariable UUID id, @RequestBody ProfessorInputDTO professorInput) {
        Professor newProfessor = ProfessorMapper.Instance.professorInputDTOToProfessor(professorInput);

        ProfessorOutputDTO response = ProfessorMapper.Instance.professorToProfessorOutputDTO(professorSvc.update(newProfessor, id));
        response.setUpdatedAt(new Date());

        return response;
    }

    @PatchMapping("/{id}/students")
    @ResponseStatus(HttpStatus.CREATED)
    public List<StudentSimpleRelationsOutputDTO> addStudents(@PathVariable UUID id, @RequestBody List<StudentInputDTO> newStudents) {
        List<Student> newStudentsList = newStudents.stream().map(StudentMapper.Instance::studentInputDTOtoStudent).toList();

        List<Student> response = professorSvc.addStudents(newStudentsList, id);

        return response.stream().map(StudentMapper.Instance::studentToStudentSimpleRelationsOutputDTO).toList();
    }

}
