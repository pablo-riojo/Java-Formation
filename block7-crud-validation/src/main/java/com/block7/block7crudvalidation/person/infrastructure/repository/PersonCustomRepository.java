package com.block7.block7crudvalidation.person.infrastructure.repository;

import com.block7.block7crudvalidation.person.domain.Person;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PersonCustomRepository {
    Optional<List<Person>> findByName(String name);
    Optional<List<Person>> findBySurname(String name);
    Optional<List<Person>> findByGreaterCreation(Date createdAt);
    Optional<List<Person>> findByLowerCreation(Date beforeDate);
}
