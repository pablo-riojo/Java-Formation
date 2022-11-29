package com.block7.block7crudvalidation.professor.infrastructure.dto;

import com.block7.block7crudvalidation.professor.domain.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProfessorMapper {
    ProfessorMapper Instance = Mappers.getMapper(ProfessorMapper.class);

    ProfessorOutputDTO professorToProfessorOutputDTO(Professor professor);
    ProfessorSimpleOutputDTO professorToProfessorSimpleOutputDTO(Professor professor);
    Professor professorInputDTOToProfessor(ProfessorInputDTO professorInputDTO);
    ProfessorUpdateDTO professorToProfessorUpdateDTO(Professor professor);
}
