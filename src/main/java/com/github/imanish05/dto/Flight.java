package com.github.imanish05.dto;

import jakarta.persistence.*;

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
    private LocalDateTime dateTime;
    private String oneWay;

    private long capacity;

    private long seatAvailable;

    private LocalDateTime scheduleTime;

    private String name;
    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getSeatAvailable() {
        return seatAvailable;
    }

    public void setSeatAvailable(long seatAvailable) {
        this.seatAvailable = seatAvailable;
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

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public LocalDateTime getDateTime() {
        return dateTime;
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

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", dateTime=" + dateTime +
                ", oneWay='" + oneWay + '\'' +
                ", capacity=" + capacity +
                ", seatAvailable=" + seatAvailable +
                ", scheduleTime=" + scheduleTime +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}