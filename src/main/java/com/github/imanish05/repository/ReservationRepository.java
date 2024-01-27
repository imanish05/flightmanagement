package com.github.imanish05.repository;

import com.github.imanish05.dto.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<BookingDetails,Long> {

}
