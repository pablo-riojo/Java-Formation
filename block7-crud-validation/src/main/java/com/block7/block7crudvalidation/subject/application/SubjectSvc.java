package com.block7.block7crudvalidation.subject.application;

import com.block7.block7crudvalidation.subject.domain.Subject;

import java.util.List;
import java.util.UUID;

public interface SubjectSvc {
    List<Subject> findAll();
    Subject findById(UUID id);
    Subject update(Subject newSubject, UUID id);
    void delete(UUID id);
    Subject save(Subject subject);
}
