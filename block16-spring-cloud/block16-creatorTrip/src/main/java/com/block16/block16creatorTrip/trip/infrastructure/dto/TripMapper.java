package com.block16.block16creatorTrip.trip.infrastructure.dto;

import com.block16.block16creatorTrip.trip.domain.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TripMapper {
    TripMapper Instance = Mappers.getMapper(TripMapper.class);

    TripOutputDTO tripToTripOutputDTO(Trip trip);
    Trip tripInputDTOToTrip(TripInputDTO tripInputDTO);
    TripUpdateDTO tripToTripUpdateDTO(Trip trip);
}
