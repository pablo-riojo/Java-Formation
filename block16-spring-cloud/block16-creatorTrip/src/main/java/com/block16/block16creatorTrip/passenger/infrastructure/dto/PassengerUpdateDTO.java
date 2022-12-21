package com.block16.block16creatorTrip.passenger.infrastructure.dto;

import com.block16.block16creatorTrip.trip.infrastructure.dto.TripOutputDTO;
import lombok.Data;

@Data
public class PassengerUpdateDTO {
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phone;
    private TripOutputDTO trip;
}
