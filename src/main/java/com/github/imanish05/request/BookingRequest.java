package com.github.imanish05.request;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


public class BookingRequest {

    private Long userId;
    private List<Long> seatNoList;
    private Long flightId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime dateOfTravel;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalDateTime timeOfTravel;

    public BookingRequest() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getSeatNoList() {
        return seatNoList;
    }

    public void setSeatNoList(List<Long> seatNoList) {
        this.seatNoList = seatNoList;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getDateOfTravel() {
        return dateOfTravel;
    }

    public void setDateOfTravel(LocalDateTime dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public LocalDateTime getTimeOfTravel() {
        return timeOfTravel;
    }

    public void setTimeOfTravel(LocalDateTime timeOfTravel) {
        this.timeOfTravel = timeOfTravel;
    }
}