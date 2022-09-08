package com.vicenzo.flightreservation.services;

import com.vicenzo.flightreservation.dto.ReservationRequest;
import com.vicenzo.flightreservation.entities.Flight;
import com.vicenzo.flightreservation.entities.Passenger;
import com.vicenzo.flightreservation.entities.Reservation;
import com.vicenzo.flightreservation.repos.FlightRepository;
import net.bytebuddy.asm.MemberSubstitution;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.ui.ModelMap;

public class ReservationServiceImpl  implements  ReservationService{

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Reservation bookFlight(ReservationRequest request) {

        // Make payment

        Long flightId = request.getId();
        Flight flight = flightRepository.findById(flightId).get();

        ModelMapper modelMapper = new ModelMapper();

        Passenger passenger = modelMapper.map(flight, Passenger.class);

        return null;
    }
}
