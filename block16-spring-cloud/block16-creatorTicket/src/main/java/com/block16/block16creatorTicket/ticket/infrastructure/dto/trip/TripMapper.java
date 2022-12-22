package com.block16.block16creatorTicket.ticket.infrastructure.dto.trip;

import com.block16.block16creatorTicket.ticket.domain.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TripMapper {
    TripMapper Instance = Mappers.getMapper(TripMapper.class);

    TripOutputDTO tripToTripOutputDTO(Trip trip);
    TripSimpleOutputDTO tripToTripSimpleOutputDTO(Trip trip);
    Trip tripOutputDTOToTrip(TripOutputDTO trip);
}
