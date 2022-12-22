package com.block16.block16creatorTicket.ticket.infrastructure.dto.ticket;

import com.block16.block16creatorTicket.ticket.infrastructure.dto.passenger.PassengerOutputDTO;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.trip.TripSimpleOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TicketClientDTO {
    private UUID id;
    private PassengerOutputDTO passenger;
    private TripSimpleOutputDTO trip;
}
