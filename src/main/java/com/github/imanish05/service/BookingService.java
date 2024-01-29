package com.github.imanish05.service;

import com.github.imanish05.dto.BookingDetails;
import com.github.imanish05.exception.BookingNotFoundException;
import com.github.imanish05.exception.FlightReservationException;
import com.github.imanish05.dto.Flight;
import com.github.imanish05.dto.User;
import com.github.imanish05.exception.NoSeatAvailableException;
import com.github.imanish05.exception.UserNotFoundException;
import com.github.imanish05.repository.FlightScheduleRepository;
import com.github.imanish05.repository.RegistrationRepository;
import com.github.imanish05.repository.ReservationRepository;
import com.github.imanish05.request.BookingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

@Service
@EnableTransactionManagement
public class BookingService {

    @Autowired
    private ReservationRepository reserveRepository;

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    public List<Flight> findFlightsBySource(String source) {

        return flightScheduleRepository.findBySource(source);
    }


    public List<Flight> findFlightsBySourceAndDestination(String source, String destination) {
        return flightScheduleRepository.findBySourceAndDestination(source, destination);
    }

    @Transactional
    public BookingDetails allocateTicket(BookingRequest bookingRequest) {

        //Validation for user details
        // Validation for flight details
        //ticket Availability
        User userDetails = registrationRepository.findById(bookingRequest.getUserId()).orElseThrow(() -> new UserNotFoundException("User does not Exist with id :" + bookingRequest.getUserId()));
        // Just to check for seat available in the flight selected by user on time mentioned
        Flight flightDetails = flightScheduleRepository.findById(bookingRequest.getFlightId()).orElseThrow(() -> new FlightReservationException("Flight does not Exist with id :" + bookingRequest.getUserId()));

        //ToDO
        //Implement Optimistic locking for time duration for seat reservation or Queue Based Locking
        //Seat shared in the request to be booked
        long seatBooked = flightDetails.getSeatBooked();
        int noOfSeatsReq = bookingRequest.getSeatNoList().size();
        Long seatAvailable = flightDetails.getCapacity() - seatBooked;
        if (seatAvailable > 0 && flightDetails.getCapacity() > 0 && noOfSeatsReq <= seatAvailable) {
            long seatsSelected = seatBooked + noOfSeatsReq;
            BookingDetails cutomerRequest = new BookingDetails();
            cutomerRequest.setUser(userDetails);
            cutomerRequest.setFlight(flightDetails);

            StringJoiner sn = new StringJoiner(",");
            for (Long seatNo = (seatBooked + 1); seatNo <= (seatBooked + noOfSeatsReq); seatNo++) {
                sn.add(flightDetails.getName() + seatNo);
            }
            cutomerRequest.setSeatNumbers(sn.toString());

            Lock l = new ReentrantLock();
            l.lock();
            try {
                //check for payment status -- if failed throw payment Failure exception
                cutomerRequest.setTotalTicketPrice(noOfSeatsReq*flightDetails.getPrice());
                int updateStatus = flightScheduleRepository.updateSeatAvailableById(seatsSelected, flightDetails.getId());
                return reserveRepository.save(cutomerRequest);

            } finally {
                l.unlock();
            }

        } else throw new NoSeatAvailableException("No More seats available. U can try other flights");
    }


    public List<BookingDetails> findAll() {

        return reserveRepository.findAll();
    }

    public BookingDetails findById(Long id) {
        return reserveRepository.findById(id).orElseThrow(() -> new BookingNotFoundException("No Booking Exist with Id: " + id));
    }
}
