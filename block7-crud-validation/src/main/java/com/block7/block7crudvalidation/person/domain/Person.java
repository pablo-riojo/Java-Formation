package com.block7.block7crudvalidation.person.domain;

import lombok.*;

import javax.persistence.*;
import java.net.URL;
import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false, length = 10)
    private String user;

    @Column(name = "user_password", nullable = false)
    private String password;

    @Column(name = "person_name", nullable = false)
    private String name;

    @Column(name = "person_surname")
    private String surname;

    @Column(name = "personal_email", unique = true, nullable = false)
    private String email;

    @Column(name = "company_email", nullable = false)
    private String companyEmail;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private final Date createdAt = new Date();

    @Column(name = "image_url")
    private URL imageUrl;

    @Column(name = "termination_date")
    private Date terminationDate;
}