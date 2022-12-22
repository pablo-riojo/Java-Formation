package com.block16.block16creatorTicket.ticket.application;

import com.block16.block16creatorTicket.ticket.domain.Passenger;
import com.block16.block16creatorTicket.ticket.domain.Ticket;
import com.block16.block16creatorTicket.ticket.domain.Trip;

import java.util.List;
import java.util.UUID;

public interface TicketSvc {
    List<Ticket> findAll();
    Ticket findById(UUID id);
    Ticket save(Passenger passenger, Trip trip);
    void deleteById(UUID id);
}
