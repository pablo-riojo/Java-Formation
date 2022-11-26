package com.block7.block7crudvalidation.professor.domain;

import com.block7.block7crudvalidation.person.domain.Person;
import lombok.*;

import javax.persistence.*;
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
    @Column(name = "professor_id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @OneToOne
    @JoinColumn(name = "person_id", unique = true)
    private Person person;

    @Column(name = "comments")
    private String comments;

    @Column(name = "branch", nullable = false)
    private String branch;

}