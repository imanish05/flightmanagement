package com.github.imanish05.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object>  handleUserNotFoundException(UserNotFoundException userNotFoundException){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userNotFoundException.getMessage());
    }

    @ExceptionHandler({FlightNotFoundException.class})
    public ResponseEntity<Object>  handleFlightNotFoundException(FlightNotFoundException flightNotFoundException){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(flightNotFoundException.getMessage());
    }

    @ExceptionHandler({NoSeatAvailableException.class})
    public ResponseEntity<Object> handleNoSeatAvailableException(NoSeatAvailableException noSeatAvailableException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(noSeatAvailableException.getMessage());
    }
}
