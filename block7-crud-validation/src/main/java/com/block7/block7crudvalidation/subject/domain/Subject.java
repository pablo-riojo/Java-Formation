package com.block7.block7crudvalidation.subject.domain;

import com.block7.block7crudvalidation.student.domain.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "subject")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

    @ManyToMany
    @JoinTable(name = "student_subjects")
    @ToString.Exclude
    private List<Student> student = new ArrayList<>();

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "comments")
    private String comments;

    @Column(name = "initial_date", nullable = false)
    private Date initialDate;

    @Column(name = "finish_date")
    private Date finishDate;

    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;

    // TODO: Set student subject
}