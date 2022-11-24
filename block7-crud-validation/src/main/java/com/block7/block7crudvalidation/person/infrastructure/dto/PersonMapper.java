package com.block7.block7crudvalidation.person.infrastructure.dto;

import com.block7.block7crudvalidation.person.domain.Person;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper Instance = Mappers.getMapper(PersonMapper.class);

    PersonOutputDTO personToPersonOutputDTO(Person person);
    Person personInputDTOToPerson(PersonInputDTO personInputDTO);
}
