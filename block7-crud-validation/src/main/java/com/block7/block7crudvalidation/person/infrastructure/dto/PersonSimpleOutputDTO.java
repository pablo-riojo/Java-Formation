package com.block7.block7crudvalidation.person.infrastructure.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PersonSimpleOutputDTO {
    private UUID id;
    private String user;
    private String name;
    private String surname;
    private String email;
    private String companyEmail;
    private String city;
    private Boolean active;
}
