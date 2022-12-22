package com.block16.block16creatorTicket.ticket.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false, columnDefinition = "uuid", unique = true)
    private UUID passengerId;

    @Column(nullable = false)
    private String passengerName;

    @Column(nullable = false)
    private String passengerSurname;

    @Column(nullable = false, unique = true)
    private String passengerEmail;

    @Column(nullable = false, unique = true)
    private UUID tripId;

    @Column(nullable = false)
    private String tripOrigin;

    @Column(nullable = false)
    private String tripDestination;
}
