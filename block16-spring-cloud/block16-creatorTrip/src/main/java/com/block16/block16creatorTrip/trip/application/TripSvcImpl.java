package com.block16.block16creatorTrip.trip.application;

import com.block16.block16creatorTrip.passenger.domain.Passenger;
import com.block16.block16creatorTrip.trip.domain.Status;
import com.block16.block16creatorTrip.trip.domain.Trip;
import com.block16.block16creatorTrip.trip.infrastructure.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class TripSvcImpl implements TripSvc {
    @Autowired TripRepository repository;

    @Override
    public List<Trip> findAll() {
        return repository.findAll();
    }

    @Override
    public Trip findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip with ID " + id + " not found")
        );
    }

    @Override
    public String countPassengers(UUID id) {
        Trip trip = this.findById(id);

        if (trip.getPassengers().size() == 0) return "No passengers found in trip" + id;
        return trip.getPassengers().size() + " passengers found in trip " + id;
    }

    @Override
    public String verifyStatus(UUID id) {
        return "status: " + this.findById(id).getStatus().name() + ", trip: " + id;
    }

    @Override
    public Trip save(Trip trip) {
        return repository.save(trip);
    }

    @Override
    public Trip update(Trip newTrip, UUID id) {
        return null;
    }

    @Override
    public Trip addPassenger(Passenger passenger, UUID id) throws Exception {
        Trip trip = this.findById(id);
        trip.getPassengers().add(passenger);
        trip.getPassengers().forEach(p -> p.setTrip(trip));
        trip.setUpdatedAt(new Date());

        if (trip.getPassengers().size() > 40)
            throw new Exception("You are exceeding the passengers limit: " + trip.getPassengers().size() + "/40");

        return this.save(trip);
    }

    @Override
    public Trip changeStatus(Status newStatus, UUID id) {
        Trip trip = this.findById(id);
        trip.setStatus(newStatus);
        trip.setUpdatedAt(new Date());

        return this.save(trip);
    }

    @Override
    public Trip acceptTrip(UUID id) {
        Trip trip = this.findById(id);
        trip.setStatus(Status.ACCEPTED);
        trip.setUpdatedAt(new Date());

        return this.save(trip);
    }

    @Override
    public void deleteById(UUID id) {
        this.findById(id);

        repository.deleteById(id);
    }
}
