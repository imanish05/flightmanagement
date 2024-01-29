package com.github.imanish05.repository;

import com.github.imanish05.dto.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightScheduleRepository extends JpaRepository<Flight,Long> {


    List<Flight> findBySource(String source);

    List<Flight> findBySourceAndDestination(String source, String destination);

    @Modifying
    @Query("update Flight f set f.seatBooked = :seatBooked where f.id =:id")
     int  updateSeatAvailableById(@Param(value = "seatBooked") long seatBooked, @Param(value = "id") long id);
}
