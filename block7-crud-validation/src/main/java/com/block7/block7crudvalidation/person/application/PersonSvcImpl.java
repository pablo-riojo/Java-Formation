package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PersonSvcImpl implements PersonSvc {
    @Autowired
    private PersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByUser(String user) {
        return personRepository.findByUser(user).orElseThrow(() -> new EntityNotFoundException("Person with username " + user + " not found"));
    }

    @Override
    @Transactional
    public Person update(Person newPerson, Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person with ID " + id + " not found"));

        boolean equals = Objects.equals(person, newPerson);
        if (equals) {
            throw new IllegalArgumentException("The new person is the same as the old one");
        }

        newPerson.setId(id);
        newPerson.setCreatedAt(person.getCreatedAt());
        newPerson.setUpdatedAt(new Date());



        return personRepository.save(newPerson);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
