package com.block16.block16creatorTrip.passenger.infrastructure.repository;

import com.block16.block16creatorTrip.passenger.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PassengerRepository extends JpaRepository<Passenger, UUID> {
}
