package com.block16.block16creatorTrip.passenger.application;

import com.block16.block16creatorTrip.passenger.domain.Passenger;

import java.util.List;
import java.util.UUID;

public interface PassengerSvc {
    List<Passenger> findAll();
    Passenger findById(UUID id);
    Passenger save(Passenger passenger);
    Passenger update(Passenger newPassenger, UUID id) throws Exception;
    void deleteById(UUID id);
}
