package com.block7.block7crudvalidation.subject.application;

import com.block7.block7crudvalidation.shared.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;
import com.block7.block7crudvalidation.subject.application.utils.SubjectCheckings;
import com.block7.block7crudvalidation.subject.domain.Subject;
import com.block7.block7crudvalidation.subject.infrastructure.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectSvcImpl implements SubjectSvc {
    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(UUID id) {
        return subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Subject with ID " + id + " not found"));
    }

    @Override
    public Subject update(Subject newSubject, UUID id) {
        Subject subject = SubjectSvcImpl.this.subjectRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Subject with ID " + id + " not found"));

        if (SubjectCheckings.isNewSubjectEqual(newSubject, subject)) throw  new UnprocessableEntityException("Cannot update. Both subjects are equal");


        // TODO: Subject update effects
        newSubject.setId(id);
        newSubject.setCreatedAt(subject.getCreatedAt());
        newSubject.setUpdatedAt(new Date());

        return subjectRepository.save(newSubject);
    }

    @Override
    public void delete(UUID id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepository.save(subject);
    }
}
