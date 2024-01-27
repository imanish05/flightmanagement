package com.github.imanish05.controller;

import com.github.imanish05.dto.BookingDetails;
import com.github.imanish05.exception.FlightReservationException;
import com.github.imanish05.request.BookingRequest;
import com.github.imanish05.dto.Flight;
import com.github.imanish05.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class Reservation {

    @Autowired
    private BookingService bookingService;
    @GetMapping("")
    public ResponseEntity<String> welcome(){

        return new ResponseEntity<>("Welcome to Reservation management System of Manish Airlines",HttpStatus.OK);
    }

    @GetMapping(value = "/find/{source}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flight>  findBySource(@PathVariable(value = "source") String source){

       return bookingService.findFlightsBySource(source);
    }

    @GetMapping(value = "/find/{source}/{destination}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Flight>  findBySource(@PathVariable(value = "source") String source,@PathVariable(value = "destination") String destination){

        return bookingService.findFlightsBySourceAndDestination(source,destination);
    }

    @PostMapping(path="/book", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public BookingDetails bookTicket(@RequestBody BookingRequest bookingRequest){

       return bookingService.allocateTicket(bookingRequest);
    }

    @GetMapping(value = "/booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookingDetails>  findAll(){

        return bookingService.findAll();
    }

    @GetMapping(value = "/booking/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public BookingDetails findById(@PathVariable (value = "id") Long id){

        return bookingService.findById(id);
    }
}
