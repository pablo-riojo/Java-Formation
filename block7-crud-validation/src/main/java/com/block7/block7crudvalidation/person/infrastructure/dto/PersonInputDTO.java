package com.block7.block7crudvalidation.person.infrastructure.dto;

import com.block7.block7crudvalidation.person.domain.Person;
import lombok.Data;

import java.io.Serializable;
import java.net.URL;

/**
 * A DTO for the {@link Person} entity
 */

@Data
public class PersonInputDTO implements Serializable {
    private String user;
    private String password;
    private String name;
    private String surname;
    private String email;
    private String companyEmail;
    private String city;
    private Boolean active;
    private URL imageUrl;
}