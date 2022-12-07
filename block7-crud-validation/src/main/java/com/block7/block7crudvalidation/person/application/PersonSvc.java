package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.professor.domain.Professor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PersonSvc {
    List<Person> findAll();
    Person findById(UUID id);
    Person findByUser(String user);
    List<Person> findByName(String name);
    List<Person> findBySurname(String surname);
    List<Person> findByGreaterCreation(Date afterDate);
    List<Person> findByLowerCreation(Date beforeDate);
    Professor findProfessor(UUID id);
    Person update(Person newPerson, UUID id);
    void delete(UUID id);
    Person save(Person person);
}
