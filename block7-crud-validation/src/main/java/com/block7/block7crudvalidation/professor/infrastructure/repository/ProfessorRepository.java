package com.block7.block7crudvalidation.professor.infrastructure.repository;

import com.block7.block7crudvalidation.professor.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfessorRepository extends JpaRepository<Professor, UUID> {
    Optional<Professor> findByPersonId(UUID id);
}