package com.block16.block16creatorTicket.ticket.infrastructure.controller;

import com.block16.block16creatorTicket.ticket.application.TicketSvc;
import com.block16.block16creatorTicket.ticket.domain.Passenger;
import com.block16.block16creatorTicket.ticket.domain.Ticket;
import com.block16.block16creatorTicket.ticket.domain.Trip;
import com.block16.block16creatorTicket.ticket.infrastructure.controller.client.PassengerClient;
import com.block16.block16creatorTicket.ticket.infrastructure.controller.client.TripClient;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.passenger.PassengerMapper;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.ticket.TicketOutputDTO;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.trip.TripMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired TicketSvc svc;
    @Autowired PassengerClient passengerClient;
    @Autowired TripClient tripClient;


//    @GetMapping
//    public List<TicketOutputDTO> getAll() {
//        return svc.findAll().stream().map(TicketMapper.Instance::ticketToTicketOutputDTO).toList();
//    }

//    @GetMapping("/{id}")
//    public TicketOutputDTO getById(@PathVariable UUID id) {
//        return TicketMapper.Instance.ticketToTicketOutputDTO(svc.findById(id));
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TicketOutputDTO create(@RequestParam("passenger") UUID passengerId, @RequestParam("trip") UUID tripId) {
        Passenger passenger = PassengerMapper.Instance.passengerOutputDTOToPassenger(
                passengerClient.getById(passengerId)
        );
        Trip trip = TripMapper.Instance.tripOutputDTOToTrip(
                tripClient.getById(tripId)
        );

        Ticket response = svc.save(passenger, trip);

        return new TicketOutputDTO(
                response.getId(),
                PassengerMapper.Instance.passengerToPassengerOutputDTO(passenger),
                TripMapper.Instance.tripToTripSimpleOutputDTO(trip)
        );
    }

//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable UUID id) {
//        svc.deleteById(id);
//    }
}
