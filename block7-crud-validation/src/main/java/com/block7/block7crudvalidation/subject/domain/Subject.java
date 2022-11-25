package com.block7.block7crudvalidation.subject.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "professor")
public class Subject {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

}