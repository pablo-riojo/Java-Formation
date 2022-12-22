package com.block16.block16creatorTrip.trip.application;

import com.block16.block16creatorTrip.passenger.domain.Passenger;
import com.block16.block16creatorTrip.trip.domain.Status;
import com.block16.block16creatorTrip.trip.domain.Trip;

import java.util.List;
import java.util.UUID;

public interface TripSvc {
    List<Trip> findAll();
    Trip findById(UUID id);
    String countPassengers(UUID id);
    String verifyStatus(UUID id);
    Trip save(Trip trip);
    Trip update(Trip newTrip, UUID id);
    Trip addPassenger(Passenger passenger, UUID id) throws Exception;
    Trip changeStatus(Status newStatus, UUID id);
    Trip acceptTrip(UUID id);
    void deleteById(UUID id);
}
