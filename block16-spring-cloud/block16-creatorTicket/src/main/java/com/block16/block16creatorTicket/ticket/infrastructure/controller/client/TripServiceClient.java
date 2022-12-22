package com.block16.block16creatorTicket.ticket.infrastructure.controller.client;

import com.block16.block16creatorTicket.ticket.infrastructure.dto.passenger.PassengerOutputDTO;
import com.block16.block16creatorTicket.ticket.infrastructure.dto.trip.TripOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@FeignClient("creatorTrip-service")
public interface TripServiceClient {
    @GetMapping("passenger/{id}")
    PassengerOutputDTO getPassengerById(@PathVariable UUID id);
    @GetMapping("trip/{id}")
    TripOutputDTO getTripById(@PathVariable UUID id);
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("trip/{id}/status/accepted")
    TripOutputDTO acceptTrip(@PathVariable UUID id);
}
