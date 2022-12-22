package com.block16.block16creatorTicket.ticket.infrastructure.controller;

import com.block16.block16creatorTicket.ticket.application.TicketSvc;
import com.block16.block16creatorTicket.ticket.domain.Passenger;
import com.block16.block16creatorTicket.ticket.domain.Ticket;
import com.block16.block16creatorTicket.ticket.domain.Trip;
import com.block16.block16creatorTicket.ticket.infrastructure.controller.client.TripServiceClient;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.passenger.PassengerMapper;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.ticket.TicketClientDTO;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.ticket.TicketOutputDTO;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.trip.TripMapper;
import com.block16.block16creatorTicket.ticket.infrastructure.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired TicketSvc svc;
    @Autowired private TripServiceClient tripServiceClient;
    @Autowired private TicketRepository ticketRepository;


    @GetMapping
    public List<TicketOutputDTO> getAll() {
       return svc.findAll().stream().map(
               ticket -> new TicketOutputDTO(
                       ticket.getId(),
                       ticket.getPassengerId(),
                       ticket.getPassengerName(),
                       ticket.getPassengerSurname(),
                       ticket.getPassengerEmail(),
                       ticket.getTripId(),
                       ticket.getTripOrigin(),
                       ticket.getTripDestination(),
                       ticket.getCreatedAt()
               )
       ).toList();
    }

    @GetMapping("/{id}")
    public TicketOutputDTO getById(@PathVariable UUID id) {
        Ticket ticket = svc.findById(id);

        return new TicketOutputDTO(
                ticket.getId(),
                ticket.getPassengerId(),
                ticket.getPassengerName(),
                ticket.getPassengerSurname(),
                ticket.getPassengerEmail(),
                ticket.getTripId(),
                ticket.getTripOrigin(),
                ticket.getTripDestination(),
                ticket.getCreatedAt()
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TicketClientDTO create(@RequestParam("passenger") UUID passengerId, @RequestParam("trip") UUID tripId) {
        Passenger passenger = PassengerMapper.Instance.passengerOutputDTOToPassenger(
                tripServiceClient.getPassengerById(passengerId)
        );
        Trip trip = TripMapper.Instance.tripOutputDTOToTrip(
                tripServiceClient.getTripById(tripId)
        );

        Ticket response = svc.save(passenger, trip);

        return new TicketClientDTO(
                response.getId(),
                PassengerMapper.Instance.passengerToPassengerOutputDTO(passenger),
                TripMapper.Instance.tripToTripSimpleOutputDTO(trip)
        );
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        svc.deleteById(id);
    }
}
