package com.block7.block7crudvalidation.person.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PersonPageDTO {
    private int count;
    private List<PersonOutputDTO> response;
}
