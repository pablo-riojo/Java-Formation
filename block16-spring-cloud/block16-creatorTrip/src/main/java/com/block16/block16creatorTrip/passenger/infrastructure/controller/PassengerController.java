package com.block16.block16creatorTrip.passenger.infrastructure.controller;

import com.block16.block16creatorTrip.passenger.application.PassengerSvc;
import com.block16.block16creatorTrip.passenger.domain.Passenger;
import com.block16.block16creatorTrip.passenger.infrastructure.dto.PassengerInputDTO;
import com.block16.block16creatorTrip.passenger.infrastructure.dto.PassengerMapper;
import com.block16.block16creatorTrip.passenger.infrastructure.dto.PassengerOutputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired PassengerSvc svc;

    @GetMapping
    public List<PassengerOutputDTO> getAll() {
        return svc.findAll().stream().map(PassengerMapper.Instance::passengerToPassengerOutputDTO).toList();
    }

    @GetMapping("/{id}")
    public PassengerOutputDTO getById(@PathVariable UUID id) {
        return PassengerMapper.Instance.passengerToPassengerOutputDTO(svc.findById(id));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PassengerOutputDTO create(@RequestBody PassengerInputDTO passenger) {
        Passenger response = svc.save(PassengerMapper.Instance.passengerInputDTOToPassenger(passenger));

        return PassengerMapper.Instance.passengerToPassengerOutputDTO(response);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public PassengerOutputDTO update(@RequestBody PassengerInputDTO passengerInput, @PathVariable UUID id) throws Exception {
        Passenger passenger = PassengerMapper.Instance.passengerInputDTOToPassenger(passengerInput);

        return PassengerMapper.Instance.passengerToPassengerOutputDTO(svc.update(passenger, id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        svc.deleteById(id);
    }
}
