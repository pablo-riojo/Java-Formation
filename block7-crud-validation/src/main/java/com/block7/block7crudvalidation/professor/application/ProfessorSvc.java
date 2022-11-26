package com.block7.block7crudvalidation.professor.application;

import com.block7.block7crudvalidation.professor.domain.Professor;

import java.util.List;
import java.util.UUID;

public interface ProfessorSvc {
    List<Professor> findAll();
    Professor findById(UUID id);
    Professor findByPersonId(UUID id);
    Professor update(Professor newProfessor, UUID id);
    void delete(UUID id);
    Professor save(Professor professor);
}
