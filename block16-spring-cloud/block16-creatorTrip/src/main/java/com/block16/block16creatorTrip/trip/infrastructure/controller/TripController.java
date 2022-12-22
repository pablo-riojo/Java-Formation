package com.block16.block16creatorTrip.trip.infrastructure.controller;

import com.block16.block16creatorTrip.passenger.infrastructure.dto.PassengerInputDTO;
import com.block16.block16creatorTrip.passenger.infrastructure.dto.PassengerMapper;
import com.block16.block16creatorTrip.trip.application.TripSvc;
import com.block16.block16creatorTrip.trip.domain.Status;
import com.block16.block16creatorTrip.trip.domain.Trip;
import com.block16.block16creatorTrip.trip.infrastructure.dto.TripInputDTO;
import com.block16.block16creatorTrip.trip.infrastructure.dto.TripMapper;
import com.block16.block16creatorTrip.trip.infrastructure.dto.TripOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
public class TripController {
    @Autowired TripSvc svc;

    @GetMapping
    public List<TripOutputDTO> getAll() {
        return svc.findAll().stream().map(TripMapper.Instance::tripToTripOutputDTO).toList();
    }

    @GetMapping("/{id}")
    public TripOutputDTO getById(@PathVariable UUID id) {
        return TripMapper.Instance.tripToTripOutputDTO(svc.findById(id));
    }

    @GetMapping("{id}/passengers/count")
    public String getPassengersCount(@PathVariable UUID id) {
        return svc.countPassengers(id);
    }

    @GetMapping("{id}/status")
    public String getStatus(@PathVariable UUID id) {
        return svc.verifyStatus(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TripOutputDTO create(@RequestBody TripInputDTO trip) {
        trip.setStatus(Status.ON_REVIEW);

        Trip response = svc.save(TripMapper.Instance.tripInputDTOToTrip(trip));

        return TripMapper.Instance.tripToTripOutputDTO(response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public TripOutputDTO update(@RequestBody TripInputDTO newTrip, @PathVariable UUID id) {
        Trip trip = TripMapper.Instance.tripInputDTOToTrip(newTrip);

        return TripMapper.Instance.tripToTripOutputDTO(svc.update(trip, id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/passengers")
    public TripOutputDTO addPassenger(@RequestBody PassengerInputDTO newPassenger, @PathVariable UUID id) {
        Trip reponse = svc.addPassenger(
                PassengerMapper.Instance.passengerInputDTOToPassenger(newPassenger),
                id
        );

        return TripMapper.Instance.tripToTripOutputDTO(reponse);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PatchMapping("/{id}/status")
    public TripOutputDTO changeStatus(@RequestParam("change") Status newStatus, @PathVariable UUID id) {
        return TripMapper.Instance.tripToTripOutputDTO(
                svc.changeStatus(newStatus, id)
        );
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/status/accepted")
    public TripOutputDTO acceptTrip(@PathVariable UUID id) {
        return TripMapper.Instance.tripToTripOutputDTO(svc.acceptTrip(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        svc.deleteById(id);
    }
}
