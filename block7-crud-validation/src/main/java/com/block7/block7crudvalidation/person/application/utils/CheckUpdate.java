package com.block7.block7crudvalidation.person.application.utils;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonMapper;
import com.block7.block7crudvalidation.person.infrastructure.dto.PersonUpdateDTO;

import java.util.Objects;

public class CheckUpdate {
    public static Boolean checkEquals(Person newPerson, Person person) {
        PersonUpdateDTO newPersonUpdateDTO = PersonMapper.Instance.personToPersonUpdateDTO(newPerson);
        PersonUpdateDTO personUpdateDTO = PersonMapper.Instance.personToPersonUpdateDTO(person);

        return Objects.equals(newPersonUpdateDTO, personUpdateDTO);
    }
}
