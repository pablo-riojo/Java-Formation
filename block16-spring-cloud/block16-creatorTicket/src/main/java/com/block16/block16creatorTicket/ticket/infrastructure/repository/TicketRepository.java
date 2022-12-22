package com.block16.block16creatorTicket.ticket.infrastructure.repository;

import com.block16.block16creatorTicket.ticket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
