package com.github.imanish05.controller;

import com.github.imanish05.dto.Flight;
import com.github.imanish05.dto.User;
import com.github.imanish05.exception.FlightReservationException;
import com.github.imanish05.service.FlightScheduleService;
import com.github.imanish05.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightSchedule {

    @Autowired
    private FlightScheduleService flightScheduleService;

    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flight addSchedule(@RequestBody Flight flight) {

        if (flight == null) {
            throw new FlightReservationException("Flight details cannot be null");
        }
        return flightScheduleService.saveSchedule(flight);
    }

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flight> findAllSchedules() {

        return flightScheduleService.findAll();
    }


    @GetMapping(path = "/find/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Flight findFlightId(@PathVariable(value = "id") Long id) {

        return flightScheduleService.findById(id);
    }



}
