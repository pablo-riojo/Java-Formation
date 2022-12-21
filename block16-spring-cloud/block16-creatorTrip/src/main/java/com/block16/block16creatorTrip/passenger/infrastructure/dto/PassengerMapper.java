package com.block16.block16creatorTrip.passenger.infrastructure.dto;

import com.block16.block16creatorTrip.passenger.domain.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassengerMapper {
    PassengerMapper Instance = Mappers.getMapper(PassengerMapper.class);

    PassengerOutputDTO passengerToPassengerOutputDTO(Passenger passenger);
    Passenger passengerInputDTOToPassenger(PassengerInputDTO passengerInputDTO);
    PassengerUpdateDTO passengerToPassengerUpdateDTO(Passenger passenger);
}
