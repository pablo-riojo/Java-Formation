package com.block16.block16creatorTicket.ticket.infrastructure.dto.trip;

import com.block16.block16creatorTicket.ticket.domain.Status;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class TripSimpleOutputDTO {
    private UUID id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private Status status;
    private Date createdAt;
    private Date updatedAt;
}
