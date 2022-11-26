package com.block7.block7crudvalidation.professor.infrastructure.controller;

import com.block7.block7crudvalidation.person.application.PersonSvc;
import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonMapper;
import com.block7.block7crudvalidation.professor.application.ProfessorSvc;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorInputDTO;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorMapper;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/professor")
public class ProfessorController {
    @Autowired
    ProfessorSvc professorSvc;
    @Autowired
    PersonSvc personSvc;

    @GetMapping("/all")
    public List<ProfessorOutputDTO> getAllProfessor() {
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
    public ProfessorOutputDTO getProfessorByPersonId() {
        return null;
    }

    @PostMapping
    public ProfessorOutputDTO createProfessor(@RequestBody ProfessorInputDTO professorInput) {
        Person personProfessor = PersonMapper.Instance.personInputDTOToPerson(professorInput.getPerson());
        UUID personId = personSvc.save(personProfessor).getId();

        Professor professor = ProfessorMapper.Instance.professorInputDTOToProfessor(professorInput);
        professor.getPerson().setId(personId);

        ProfessorOutputDTO response = ProfessorMapper.Instance.professorToProfessorOutputDTO(professorSvc.save(professor));

        return response;
    }

    @PutMapping("/{id}")
    public ProfessorOutputDTO updateProfessor(@PathVariable UUID id, ProfessorInputDTO professorInput) {
        return null;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProfessor(@PathVariable UUID id) {
        professorSvc.delete(id);
    }
}
