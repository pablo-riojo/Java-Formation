package com.block7.block7crudvalidation.professor.infrastructure.controller;

import com.block7.block7crudvalidation.professor.application.ProfessorSvc;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorInputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorMapper;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorOutputDTO;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentMapper;
import com.block7.block7crudvalidation.student.infrastructure.dto.StudentSimpleOutputDTO;
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
    public ProfessorOutputDTO getProfessorById(@PathVariable UUID id) {
        Professor professor = professorSvc.findById(id);

        ProfessorOutputDTO response = ProfessorMapper.Instance.professorToProfessorOutputDTO(professor);

        return response;
    }

    @GetMapping("/person/{id}")
    public ProfessorOutputDTO getProfessorByPersonId(@PathVariable UUID id) {
        Professor professor = professorSvc.findByPersonId(id);

        ProfessorOutputDTO response = ProfessorMapper.Instance.professorToProfessorOutputDTO(professor);

        return response;
    }

    @GetMapping("/{id}/students")
    public List<StudentSimpleOutputDTO> getStudents(@PathVariable UUID id) {
        return professorSvc.findStudents(id).stream().map(
                StudentMapper.Instance::studentToStudentSimpleOutputDTO
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable UUID id) {
        professorSvc.delete(id);
    }
}
