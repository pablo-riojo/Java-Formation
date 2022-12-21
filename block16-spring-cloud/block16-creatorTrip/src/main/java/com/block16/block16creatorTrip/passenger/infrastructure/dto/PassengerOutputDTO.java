package com.block16.block16creatorTrip.passenger.infrastructure.dto;

import com.block16.block16creatorTrip.trip.infrastructure.dto.TripOutputDTO;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class PassengerOutputDTO {
    private UUID id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
    private TripOutputDTO trip;
    private Date createdAt;
    private Date updatedAt;
}
