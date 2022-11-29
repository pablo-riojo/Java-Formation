package com.block7.block7crudvalidation.student.domain;

import com.block7.block7crudvalidation.person.domain.Person;
import com.block7.block7crudvalidation.professor.domain.Professor;
import com.block7.block7crudvalidation.subject.domain.Subject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "student_subjects")
    @ToString.Exclude
    private List<Subject> subject;

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

    public void setProfessorStudents(Student student) {
        student.professor.setProfessorPerson(student.professor);
        student.professor.setStudents(List.of(student));

        student.person.setIsStudent(true);
    }

    public void setUpdateEffects(Student newStudent, UUID id, Student student) {
        newStudent.setId(id);
        newStudent.getPerson().setId(student.getPerson().getId());
        newStudent.getProfessor().setId(student.getProfessor().getId());
        newStudent.getProfessor().getPerson().setId(student.getProfessor().getPerson().getId());
        newStudent.setCreatedAt(student.getCreatedAt());
        newStudent.getPerson().setCreatedAt(student.getPerson().getCreatedAt());
        newStudent.getPerson().setUpdatedAt(new Date());
        newStudent.setUpdatedAt(new Date());
    }

    public void setDeleteEffects(Student student) {
        student.setSubject(null);
        student.getProfessor().setStudents(null);
        student.setProfessor(null);
    }
}