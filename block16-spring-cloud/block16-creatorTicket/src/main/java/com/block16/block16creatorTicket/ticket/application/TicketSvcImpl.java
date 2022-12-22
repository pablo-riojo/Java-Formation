package com.block16.block16creatorTicket.ticket.application;

import com.block16.block16creatorTicket.ticket.domain.Passenger;
import com.block16.block16creatorTicket.ticket.domain.Ticket;
import com.block16.block16creatorTicket.ticket.domain.Trip;
import com.block16.block16creatorTicket.ticket.infrastructure.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class TicketSvcImpl implements TicketSvc {
    @Autowired TicketRepository repository;

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Override
    public Ticket findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger with ID " + id + " not found")
        );
    }

    @Override
    public Ticket save(Passenger passenger, Trip trip) {
        Ticket newTicket = new Ticket();
        newTicket.setPassengerId(passenger.getId());
        newTicket.setPassengerName(passenger.getName());
        newTicket.setPassengerSurname(passenger.getSurname());
        newTicket.setPassengerEmail(passenger.getEmail());
        newTicket.setTripId(trip.getId());
        newTicket.setTripOrigin(trip.getOrigin());
        newTicket.setTripDestination(trip.getDestination());

        return repository.save(newTicket);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
