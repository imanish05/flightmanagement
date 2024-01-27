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

@Service
@EnableTransactionManagement
public class BookingService {

    @Autowired
    private ReservationRepository reserveRepository;

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    public List<Flight> findFlightsBySource(String source){

        return flightScheduleRepository.findBySource( source);
    }


    public List<Flight> findFlightsBySourceAndDestination(String source, String destination) {
        return flightScheduleRepository.findBySourceAndDestination(source,destination);
    }

    @Transactional
    public BookingDetails allocateTicket(BookingRequest bookingRequest) {

        User userDetails = null;
        Flight flightDetails = null;

        //Validation for user details
        // Validation for flight details
        //ticket Availability
        Optional<User> user = registrationRepository.findById(bookingRequest.getUserId());
        if (user.isPresent()) {
            userDetails = user.get();
        } else
             throw new UserNotFoundException("User does not Exist with id :"+bookingRequest.getUserId());
        Optional<Flight> flight = flightScheduleRepository.findById(bookingRequest.getFlightId());

        if (flight.isPresent()){
           flightDetails=  flight.get();
            //Make it Transational and Synchronised
           Long seatNo = flightDetails.getCapacity() - flightDetails.getSeatAvailable();
            if (seatNo >= 0 && flightDetails.getCapacity()>0 && flightDetails.getSeatAvailable()>0) {
                seatNo++;
                BookingDetails cutomerRequest = new BookingDetails();
                cutomerRequest.setUser(userDetails);
                cutomerRequest.setFlight(flightDetails);
                cutomerRequest.setSeatNo(flightDetails.getName()+seatNo);

                int updateStatus = flightScheduleRepository.updateSeatAvailableById(flightDetails.getSeatAvailable() - 1, flightDetails.getId());
                return reserveRepository.save( cutomerRequest);
            }else
                throw new NoSeatAvailableException("No More seats available. U can try other flights");
        }else {
            throw new FlightReservationException("Flight does not Exist with id :"+bookingRequest.getUserId());
        }
    }

    public List<BookingDetails> findAll() {

        return reserveRepository.findAll();
    }

    public BookingDetails findById(Long id) {
        Optional<BookingDetails> byId = reserveRepository.findById(id);
        if(byId.isPresent())
            return byId.get();
        else
            throw  new BookingNotFoundException("No Booking Exist with Id: "+id);
    }
}
