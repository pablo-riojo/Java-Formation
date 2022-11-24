package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Person> findByUser(String user) {
        return personRepository.findByUser(user);
    }

    @Override
    @Transactional
    public Person update(Person newPerson, Long id) {
        Optional<Person> person = personRepository.findById(id);

        boolean equals = Objects.equals(person.get(), newPerson);
        if (equals) {
            throw new DuplicateRequestException();
        }

        newPerson.setId(id);
        newPerson.setCreatedAt(person.get().getCreatedAt());
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
