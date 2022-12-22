package com.block16.block16creatorTicket.ticket.infrastructure.controller.client;

import com.block16.block16creatorTicket.ticket.infrastructure.dto.passenger.PassengerOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "creatorTrip-service")
public interface PassengerClient {
    @GetMapping("/{id}")
    PassengerOutputDTO getById(@PathVariable UUID id);
}
