package com.block16.block16creatorTrip.trip.infrastructure.dto;

import com.block16.block16creatorTrip.passenger.infrastructure.dto.PassengerInputDTO;
import com.block16.block16creatorTrip.trip.domain.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TripInputDTO {
    private String origin;
    private String destination;
    private Date departureDate;
    private Date arrivalDate;
    private List<PassengerInputDTO> passengers;
    private Status status;
}
