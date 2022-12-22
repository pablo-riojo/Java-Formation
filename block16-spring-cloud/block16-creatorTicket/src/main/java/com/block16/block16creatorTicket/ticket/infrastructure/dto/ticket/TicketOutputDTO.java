package com.block16.block16creatorTicket.ticket.infrastructure.dto.ticket;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
public class TicketOutputDTO {
    private UUID id;
    private UUID passenger;
    private String name;
    private String surname;
    private String email;
    private UUID trip;
    private String origin;
    private String destination;
    private Date createdAt;
}
