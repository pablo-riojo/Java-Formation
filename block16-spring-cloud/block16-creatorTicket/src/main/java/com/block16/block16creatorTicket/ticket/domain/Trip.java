package com.block16.block16creatorTicket.ticket.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Trip {
    private UUID id;
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private List<Passenger> passengers;
    private Status status;
    private final Date createdAt = new Date();
    private Date updatedAt;
}