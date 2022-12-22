package com.block16.block16creatorTicket.ticket.infrastructure.dto.passenger;

import com.block16.block16creatorTicket.ticket.domain.Passenger;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PassengerMapper {
    PassengerMapper Instance = Mappers.getMapper(PassengerMapper.class);

    PassengerOutputDTO passengerToPassengerOutputDTO(Passenger passenger);
    Passenger passengerOutputDTOToPassenger(PassengerOutputDTO passenger);
}
