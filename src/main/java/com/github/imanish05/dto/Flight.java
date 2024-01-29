package com.github.imanish05.dto;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "flight")

public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String source;
    private String destination;
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private LocalDateTime date;
    private String oneWay;

    private long capacity;

    private long seatBooked;

    private double price;
    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME)
    private LocalDateTime scheduleTime;

    private String name;
    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(long seatBooked) {
        this.seatBooked = seatBooked;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    private long duration ;

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setOneWay(String oneWay) {
        this.oneWay = oneWay;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getOneWay() {
        return oneWay;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public LocalDateTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalDateTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", date=" + date +
                ", oneWay='" + oneWay + '\'' +
                ", capacity=" + capacity +
                ", seatBooked=" + seatBooked +
                ", scheduleTime=" + scheduleTime +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", price="+price+
                '}';
    }
}