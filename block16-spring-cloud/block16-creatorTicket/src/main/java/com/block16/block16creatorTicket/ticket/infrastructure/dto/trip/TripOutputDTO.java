package com.block16.block16creatorTicket.ticket.infrastructure.dto.trip;

import com.block16.block16creatorTicket.ticket.domain.Status;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.passenger.PassengerOutputDTO;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class TripOutputDTO {
    private UUID id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private List<PassengerOutputDTO> passengers;
    private Status status;
    private Date createdAt;
    private Date updatedAt;
}
