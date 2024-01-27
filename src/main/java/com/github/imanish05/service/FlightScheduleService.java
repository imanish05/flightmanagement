package com.github.imanish05.service;

import com.github.imanish05.dto.Flight;
import com.github.imanish05.exception.FlightNotFoundException;
import com.github.imanish05.exception.UserNotFoundException;
import com.github.imanish05.repository.FlightScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightScheduleService {


    @Autowired
    private FlightScheduleRepository repository;
    public Flight saveSchedule(Flight flight) {
        Flight savedFlight = repository.save(flight);
        return savedFlight;
    }


    public List<Flight> findAll() {
        return repository.findAll();
    }

    public Flight findById(Long id) {
        Optional<Flight> byId = repository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else
            throw  new FlightNotFoundException("No Such Flight Exist with Id: "+id);
    }
}
