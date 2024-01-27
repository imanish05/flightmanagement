package com.github.imanish05.request;

import jakarta.persistence.*;


public class BookingRequest {

    private Long userId;
    private  long seatNo;

    private Long flightId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(long seatNo) {
        this.seatNo = seatNo;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }




}