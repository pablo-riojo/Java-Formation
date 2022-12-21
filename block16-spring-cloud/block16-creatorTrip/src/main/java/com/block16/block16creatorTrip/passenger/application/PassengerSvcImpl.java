package com.block16.block16creatorTrip.passenger.application;

import com.block16.block16creatorTrip.passenger.domain.Passenger;
import com.block16.block16creatorTrip.passenger.infrastructure.dto.PassengerMapper;
import com.block16.block16creatorTrip.passenger.infrastructure.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class PassengerSvcImpl implements PassengerSvc {
    @Autowired PassengerRepository repository;

    @Override
    public List<Passenger> findAll() {
        return repository.findAll();
    }

    @Override
    public Passenger findById(UUID id) {
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger with ID " + id + " not found")
        );
    }

    @Override
    public Passenger save(Passenger passenger) {
        return repository.save(passenger);
    }

    @Override
    public Passenger update(Passenger newPassenger, UUID id) throws Exception {
        Passenger passenger = this.findById(id);

        boolean equals = Objects.equals(
                PassengerMapper.Instance.passengerToPassengerUpdateDTO(passenger),
                PassengerMapper.Instance.passengerToPassengerUpdateDTO(newPassenger)
        );

        if (equals) throw new Exception("Cannot update. Both passengers are equal");

        newPassenger.setId(passenger.getId());
        newPassenger.setUpdatedAt(new Date());

        return repository.save(newPassenger);
    }

    @Override
    public void deleteById(UUID id) {
        repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Passenger with ID " + id + " not found")
        );

        repository.deleteById(id);
    }
}
