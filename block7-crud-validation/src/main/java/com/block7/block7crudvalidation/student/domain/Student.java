package com.block7.block7crudvalidation.student.domain;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.professor.domain.Professor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    @ToString.Exclude
    private Professor professor;

    @Column(name = "week_hours", nullable = false)
    private int weekHours;

    @Column(name = "comments")
    private String comments;

    @Column(name = "branch", nullable = false)
    private String branch;

    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    // TODO: Set person.student
}