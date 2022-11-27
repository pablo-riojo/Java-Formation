package com.block7.block7crudvalidation.person.infrastructure.dto;

import lombok.Data;

import java.net.URL;

@Data
public class PersonUpdateDTO {
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
