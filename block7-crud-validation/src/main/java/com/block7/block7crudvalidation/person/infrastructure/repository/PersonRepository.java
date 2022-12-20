package com.block7.block7crudvalidation.person.infrastructure.repository;

import com.block7.block7crudvalidation.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID>, PersonCustomRepository {
    Optional<Person> findByUser(String user);
}