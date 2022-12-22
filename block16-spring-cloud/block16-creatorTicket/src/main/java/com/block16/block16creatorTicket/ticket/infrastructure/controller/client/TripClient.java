package com.block16.block16creatorTicket.ticket.infrastructure.controller.client;

import com.block16.block16creatorTicket.ticket.infrastructure.dto.trip.TripOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@FeignClient(name = "trip-service", url = "http://localhost:8081/trip")
public interface TripClient {
    @GetMapping("/{id}")
    TripOutputDTO getById(@PathVariable UUID id);

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/status/accepted")
    TripOutputDTO acceptTrip(@PathVariable UUID id);
}
