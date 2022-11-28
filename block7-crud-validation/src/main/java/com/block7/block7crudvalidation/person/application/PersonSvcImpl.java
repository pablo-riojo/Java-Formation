package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.application.utils.PersonCheckings;
import com.block7.block7crudvalidation.person.application.utils.EntityException;
import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.block7.block7crudvalidation.shared.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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
    public Person findById(UUID id) {
        return personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public Person findByUser(String user) {
        return personRepository.findByUser(user).orElseThrow(() -> new EntityNotFoundException("Person with username " + user + " not found"));
    }

    @Override
    @Transactional
    public Person update(Person newPerson, UUID id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person with ID " + id + " not found"));

        if (PersonCheckings.isNewPersonEqual(newPerson, person)) throw new UnprocessableEntityException("Cannot update. Both persons are equal");
        if (!PersonCheckings.isSameEmail(newPerson, person)) throw new UnprocessableEntityException("Cannot update. It must be same email: " + person.getEmail());

        newPerson.setUpdateEffects(newPerson, person, id);

       return personRepository.save(newPerson);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        personRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        EntityException.onSave(person);

        return personRepository.save(person);
    }
}
