package com.block7.block7crudvalidation.professor.application;

import com.block7.block7crudvalidation.shared.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
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
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor with ID " + id + " not found"));

        boolean professorEquals = Objects.equals(professor, newProfessor);
        if (professorEquals) throw new UnprocessableEntityException("Cannot update. The new professor is the same as the old one");

        boolean personProfessorEquals = Objects.equals(professor.getPerson(), newProfessor.getPerson());

        newProfessor.setId(id);
        newProfessor.getPerson().setId(professor.getPerson().getId());
        newProfessor.setCreatedAt(professor.getCreatedAt());
        newProfessor.getPerson().setCreatedAt(professor.getPerson().getCreatedAt());
        if (!personProfessorEquals) newProfessor.getPerson().setUpdatedAt(new Date());
        newProfessor.setUpdatedAt(new Date());

        return professorRepository.save(newProfessor);
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

