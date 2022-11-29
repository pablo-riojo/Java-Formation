package com.block7.block7crudvalidation.professor.application;

import com.block7.block7crudvalidation.professor.application.utils.ProfessorCheckings;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.repository.ProfessorRepository;
import com.block7.block7crudvalidation.shared.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;
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
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor with ID " + id + " not found"));

        if (ProfessorCheckings.isNewProfessorEqual(newProfessor, professor)) throw new UnprocessableEntityException("Cannot update. Both professors are equal");
        if (!ProfessorCheckings.isSameEmail(newProfessor, professor)) throw new UnprocessableEntityException("Cannot update. It must be same email: " + professor.getPerson().getEmail());

        newProfessor.setUpdateEffects(newProfessor, id, professor);


        return professorRepository.save(newProfessor);
    }

    @Override
    public void delete(UUID id) {
        Professor professor = professorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor with ID " + id + " not found"));

        professor.setDeleteEffects(professor);

        professorRepository.save(professor);

        professorRepository.deleteById(id);
    }

    @Override
    public Professor save(Professor professor) {
        professor.setProfessorPerson(professor);

        return professorRepository.save(professor);
    }
}

