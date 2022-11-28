package com.block7.block7crudvalidation.person.domain;

import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.student.domain.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue
    @Column(name = "person_id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(name = "professor_id", columnDefinition = "uuid")
    private UUID professorId;


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

    @Column(name = "isProfessor")
    private Boolean isProfessor = false;

    @OneToOne(mappedBy = "person")
    private Professor professor;

    @Column(name = "isStudent")
    private Boolean isStudent = false;

    @OneToOne(mappedBy = "person")
    private Student student;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "image_url")
    private URL imageUrl;

    @Column(name = "termination_date", nullable = false)
    private final Date terminationDate = setTerminationDate();

    public void setUpdateEffects(Person newPerson, Person personDb, UUID id) {
        newPerson.setId(id);
        newPerson.setCreatedAt(personDb.getCreatedAt());
        newPerson.setUpdatedAt(new Date());
    }

    public Date setTerminationDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(this.createdAt);
        c.add(Calendar.YEAR, 1);

        return c.getTime();
    }
}