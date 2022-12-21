package com.block16.block16creatorTrip.trip.domain;

import com.block16.block16creatorTrip.passenger.domain.Passenger;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "trip")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false, columnDefinition = "uuid")
    private UUID id;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private Date departureDate;

    @Column(nullable = false)
    private Date arrivalDate;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Passenger> passengers;

    @Column(nullable = false)
    private Status status;

    @Column(name = "created_at", nullable = false)
    private Date createdAt = new Date();

    @Column(name = "updated_at")
    private Date updatedAt;
}