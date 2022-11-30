package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.professor.domain.Professor;

import java.util.List;
import java.util.UUID;

public interface PersonSvc {
    List<Person> findAll();
    Person findById(UUID id);
    Person findByUser(String user);
    Professor findProfessor(UUID id);
    Person update(Person newPerson, UUID id);
    void delete(UUID id);
    Person save(Person person);
}
