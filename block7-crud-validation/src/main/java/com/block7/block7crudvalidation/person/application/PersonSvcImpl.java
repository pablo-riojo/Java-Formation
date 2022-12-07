package com.block7.block7crudvalidation.person.application;

import com.block7.block7crudvalidation.person.application.utils.EntityException;
import com.block7.block7crudvalidation.person.application.utils.PersonCheckings;
import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.repository.PersonRepository;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.shared.exception.entityNotFound.EntityNotFoundException;
import com.block7.block7crudvalidation.shared.exception.unprocessableEntity.UnprocessableEntityException;
import com.block7.block7crudvalidation.student.domain.Student;
import com.block7.block7crudvalidation.student.infrastructure.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
@Service
public class PersonSvcImpl implements PersonSvc {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private StudentRepository studentRepository;

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
    @Transactional(readOnly = true)
    public List<Person> findByName(String name) {
        return personRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Person with name " + name + " not found"));
    }

    @Override
    public List<Person> findBySurname(String surname) {
        return personRepository.findBySurname(surname).orElseThrow(() -> new EntityNotFoundException("Person with surname " + surname + " not found"));
    }

    @Override
    public List<Person> findByGreaterCreation(Date afterDate) {
        return personRepository.findByGreaterCreation(afterDate).orElseThrow(() -> new EntityNotFoundException("Person with creation date greater than " + afterDate + " not found"));
    }

    @Override
    public List<Person> findByLowerCreation(Date beforeDate) {
        return personRepository.findByLowerCreation(beforeDate).orElseThrow(() -> new EntityNotFoundException("Person with creation date lower than " + beforeDate + " not found"));
    }

    ;

    @Override
    public Professor findProfessor(UUID id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Person with ID " + id + " not found"));

        if (!person.getIsProfessor()) throw new EntityNotFoundException("Person with ID " + id + " is not a professor");

        return person.getProfessor();
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
        Person person = personRepository.findById(id).get();
        if (person.getIsStudent()) {
            Student student = studentRepository.findByPersonId(person.getId()).get();
            student.setSubject(null);

            studentRepository.save(student);
        }

        personRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Person save(Person person) {
        EntityException.onSave(person);

        return personRepository.save(person);
    }
}
