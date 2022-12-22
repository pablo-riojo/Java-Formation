package com.block16.block16creatorTrip.trip.infrastructure.dto;

import com.block16.block16creatorTrip.passenger.infrastructure.dto.PassengerInputDTO;
import com.block16.block16creatorTrip.trip.domain.Status;
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
    private List<PassengerInputDTO> passengers;
    private Status status;
    private Date createdAt;
    private Date updatedAt;
}
