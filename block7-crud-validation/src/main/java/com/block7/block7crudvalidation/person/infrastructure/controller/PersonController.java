package com.block7.block7crudvalidation.person.infrastructure.controller;

import com.block7.block7crudvalidation.person.application.PersonSvc;
import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonInputDTO;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonMapper;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import com.block7.block7crudvalidation.person.infrastructure.exception.entityNotFound.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonSvc personSvc;

    @GetMapping("/all")
    public List<PersonOutputDTO> getAllPerson() {
        return personSvc.findAll().stream().map(
               p -> PersonMapper.Instance.personToPersonOutputDTO(p)
        ).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> getPersonById(@PathVariable Long id) {
        Person person = personSvc.findById(id);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(person);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<PersonOutputDTO> getPersonByUser(@PathVariable String user) {
        Person person = personSvc.findByUser(user);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(person);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PersonOutputDTO> createPerson(@RequestBody PersonInputDTO person) {
        Person personMapped = PersonMapper.Instance.personInputDTOToPerson(person);
        personSvc.save(personMapped);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(personMapped);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@PathVariable Long id, @RequestBody PersonInputDTO newPerson) {
        Person personDB = personSvc.findById(id);

        Person newPersonMapped = PersonMapper.Instance.personInputDTOToPerson(newPerson);
        personSvc.update(newPersonMapped, id);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(newPersonMapped);
        response.setUpdatedAt(new Date());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable Long id) throws EntityNotFoundException {
        Person person = personSvc.findById(id);

        personSvc.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
