package com.block7.block7crudvalidation.professor.application.utils;

import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorMapper;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorUpdateDTO;

import java.util.Objects;

public class ProfessorCheckings {
    public static Boolean isNewProfessorEqual(Professor newProfessor, Professor professor) {
        ProfessorUpdateDTO newProfessorUpdateDTO = ProfessorMapper.Instance.professorToProfessorUpdateDTO(newProfessor);
        ProfessorUpdateDTO professorUpdateDTO = ProfessorMapper.Instance.professorToProfessorUpdateDTO(professor);

        return Objects.equals(newProfessorUpdateDTO, professorUpdateDTO);
    }

    public static Boolean isSameEmail(Professor newProfessor, Professor professor) {
        return Objects.equals(newProfessor.getPerson().getEmail(), professor.getPerson().getEmail());
    }
}
