package com.block7.block7crudvalidation.professor.domain;

import com.block7.block7crudvalidation.person.domain.Person;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "professor")
public class Professor {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "uuid", unique = true)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    @Column(name = "comments")
    private String comments;

    @Column(name = "branch", nullable = false)
    private String branch;

    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;
}