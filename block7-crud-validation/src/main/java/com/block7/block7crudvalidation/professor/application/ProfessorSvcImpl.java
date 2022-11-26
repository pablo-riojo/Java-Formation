package com.block7.block7crudvalidation.professor.application;

import com.block7.block7crudvalidation.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorSvcImpl implements ProfessorSvc {
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    @Override
    public Professor findById(UUID id) {
        return professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor with ID " + id + " not found"));
    }

    @Override
    public Professor findByPersonId(UUID id) {
        return professorRepository.findByPersonId(id).orElseThrow(() -> new EntityNotFoundException("Professor with person ID " + id + " not found"));
    }

    @Override
    public Professor update(Professor newProfessor, UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {
        professorRepository.deleteById(id);
    }

    @Override
    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }
}

