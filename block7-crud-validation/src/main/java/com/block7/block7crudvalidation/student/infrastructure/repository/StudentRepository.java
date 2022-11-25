package com.block7.block7crudvalidation.student.infrastructure.repository;

import com.block7.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
}