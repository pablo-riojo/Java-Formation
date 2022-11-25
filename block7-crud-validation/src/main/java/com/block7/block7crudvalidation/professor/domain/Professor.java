package com.block7.block7crudvalidation.professor.domain;

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
    @Column(name = "id", nullable = false, columnDefinition = "uuid")
    private UUID id;

}