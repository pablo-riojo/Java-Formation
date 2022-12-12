package com.block13.block13mongodb.person.application;

import com.block13.block13mongodb.person.domain.Person;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonSvc {
    List<Person> findAll();
    Page<Person> findAllPaginated(int offset, int pageSize);
    Person findById(int id);
    Person update(Person newPerson, int id);
    void delete(int id);
    Person save(Person person);
}
