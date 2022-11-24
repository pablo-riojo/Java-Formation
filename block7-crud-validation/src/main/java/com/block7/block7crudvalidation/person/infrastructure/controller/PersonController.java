package com.block7.block7crudvalidation.person.infrastructure.controller;

import com.block7.block7crudvalidation.person.application.PersonSvc;
import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonInputDTO;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonMapper;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonSvc personSvc;
    @Autowired
    PersonMapper mapper;

    @GetMapping("/all")
    public List<PersonOutputDTO> getAllPerson() {
        return personSvc.findAll().stream().map(
               p -> mapper.Instance.personToPersonOutputDTO(p)
        ).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> getPersonById(@PathVariable Long id) {
        Optional<Person> person = personSvc.findById(id);
        if (person.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");

        PersonOutputDTO response = mapper.Instance.personToPersonOutputDTO(person.get());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{user}")
    public ResponseEntity<PersonOutputDTO> getPersonByUser(@PathVariable String user) {
        Optional<Person> person = personSvc.findByUser(user);
        if (person.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with username " + user + " not found");

        PersonOutputDTO response = mapper.Instance.personToPersonOutputDTO(person.get());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PersonOutputDTO> createPerson(@RequestBody PersonInputDTO person) {
        Person personMapped = mapper.Instance.personInputDTOToPerson(person);
        personSvc.save(personMapped);

        PersonOutputDTO response = mapper.Instance.personToPersonOutputDTO(personMapped);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDTO> updatePerson(@PathVariable Long id, @RequestBody PersonInputDTO newPerson) {
        Optional<Person> personDB = personSvc.findById(id);
        if (personDB.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");

        Person newPersonMapped = mapper.Instance.personInputDTOToPerson(newPerson);
        personSvc.update(newPersonMapped, id);

        PersonOutputDTO response = mapper.Instance.personToPersonOutputDTO(newPersonMapped);
        response.setUpdatedAt(new Date());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonById(@PathVariable Long id) {
        Optional<Person> person = personSvc.findById(id);
        if (person.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found");

        personSvc.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
