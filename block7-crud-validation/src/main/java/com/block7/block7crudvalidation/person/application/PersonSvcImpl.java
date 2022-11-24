package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public void delete(Long id) {
        personRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        return personRepository.save(person);
    }
}
