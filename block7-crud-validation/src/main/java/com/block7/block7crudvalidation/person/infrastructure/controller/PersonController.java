package com.block7.block7crudvalidation.person.infrastructure.controller;

import com.block7.block7crudvalidation.person.application.PersonSvc;
import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonInputDTO;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonMapper;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonOutputDTO;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonPageDTO;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorMapper;
import com.block7.block7crudvalidation.professor.infrastructure.dto.ProfessorOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("ALL")
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonSvc personSvc;

    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    @GetMapping("/all")
    public List<PersonOutputDTO> getAllPersons() {
        return personSvc.findAll().stream().map(
               p -> PersonMapper.Instance.personToPersonOutputDTO(p)
        ).toList();
    }

    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    @GetMapping
    public PersonPageDTO getAllPaginated(@RequestParam(value = "offset") int offset, @RequestParam(value = "pageSize") int pageSize) {
       Page<Person> page = personSvc.findAllPaginated(offset, pageSize);

       List<PersonOutputDTO> response = page.stream().map(PersonMapper.Instance::personToPersonOutputDTO).toList();
       Page<PersonOutputDTO> responsePag = new PageImpl<>(response);

       return new PersonPageDTO(responsePag.getSize(), responsePag.getContent());
    }

    @GetMapping("/{id}")
    public PersonOutputDTO getPersonById(@PathVariable UUID id) {
        Person person = personSvc.findById(id);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(person);

        return response;
    }

    @GetMapping("/user/{user}")
    public PersonOutputDTO getPersonByUser(@PathVariable String user) {
        Person person = personSvc.findByUser(user);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(person);

        return response;
    }

    @GetMapping("name/{name}")
    public List<PersonOutputDTO> getPersonByName(@PathVariable String name) {
        List<Person> person = personSvc.findByName(name);

        List<PersonOutputDTO> response = person.stream().map(PersonMapper.Instance::personToPersonOutputDTO).toList();
//        response.sort((r1, r2) -> r1.getUser().compareTo(r2.getUser()));

        return response;
    }

    @GetMapping("surname/{surname}")
    public List<PersonOutputDTO> getPersonBySurname(@PathVariable String surname) {
        List<Person> person = personSvc.findBySurname(surname);

        List<PersonOutputDTO> response = person.stream().map(PersonMapper.Instance::personToPersonOutputDTO).toList();

        return response;
    }

    @GetMapping("creation")
    public List<PersonOutputDTO> getPersonGreaterCreation(@RequestParam(value = "after", required = false) Date afterDate, @RequestParam(value = "before", required = false) Date beforeDate) {
        List<Person> person = new ArrayList<>();

        if (afterDate != null && beforeDate == null) {
            person = personSvc.findByGreaterCreation(afterDate);
        }
        if (beforeDate != null && afterDate == null) {
            person = personSvc.findByLowerCreation(beforeDate);
        }


        List<PersonOutputDTO> response = person.stream().map(PersonMapper.Instance::personToPersonOutputDTO).toList();

        return response;
    }

    @GetMapping("/professor/{id}")
    public ProfessorOutputDTO getProfessor(@PathVariable UUID id) {
        Professor response = personSvc.findProfessor(id);

        return ProfessorMapper.Instance.professorToProfessorOutputDTO(response);
    }

    @CrossOrigin(origins = "*", methods = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDTO createPerson(@RequestBody PersonInputDTO personInput) {
        Person newPerson = PersonMapper.Instance.personInputDTOToPerson(personInput);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(personSvc.save(newPerson));

        return response;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDTO updatePerson(@PathVariable UUID id, @RequestBody PersonInputDTO personInput) {
        Person newPerson = PersonMapper.Instance.personInputDTOToPerson(personInput);

        PersonOutputDTO response = PersonMapper.Instance.personToPersonOutputDTO(personSvc.update(newPerson, id)
        );
        response.setUpdatedAt(new Date());

        return response;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable UUID id) {
        Person person = personSvc.findById(id);

        personSvc.delete(id);
    }
}
