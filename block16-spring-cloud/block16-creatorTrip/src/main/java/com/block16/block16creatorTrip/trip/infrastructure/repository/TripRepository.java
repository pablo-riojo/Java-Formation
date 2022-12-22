package com.block16.block16creatorTrip.trip.infrastructure.repository;

import com.block16.block16creatorTrip.trip.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
}
