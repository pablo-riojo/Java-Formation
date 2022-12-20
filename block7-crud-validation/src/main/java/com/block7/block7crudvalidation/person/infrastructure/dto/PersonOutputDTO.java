package com.block7.block7crudvalidation.person.infrastructure.dto;

import lombok.*;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * A DTO for the {@link com.block7.block7crudvalidation.person.domain.Person} entity
 */

@Data
public class PersonOutputDTO implements Serializable {
    private UUID id;
    private String user;
    private String name;
    private Boolean admin;
    private String surname;
    private String email;
    private String companyEmail;
    private String city;
    private Boolean active;
    private Date createdAt;
    private Date updatedAt;
    private URL imageUrl;
    private Date terminationDate;
}