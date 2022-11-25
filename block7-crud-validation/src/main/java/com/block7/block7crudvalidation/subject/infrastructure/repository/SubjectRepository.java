package com.block7.block7crudvalidation.subject.infrastructure.repository;

import com.block7.block7crudvalidation.subject.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubjectRepository extends JpaRepository<Subject, UUID> {
}