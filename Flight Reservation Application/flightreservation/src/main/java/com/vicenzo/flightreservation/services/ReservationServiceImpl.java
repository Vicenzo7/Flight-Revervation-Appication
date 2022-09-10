package com.vicenzo.flightreservation.services;

import com.vicenzo.flightreservation.dto.ReservationRequest;
import com.vicenzo.flightreservation.entities.Flight;
import com.vicenzo.flightreservation.entities.Passenger;
import com.vicenzo.flightreservation.entities.Reservation;
import com.vicenzo.flightreservation.repos.FlightRepository;
import com.vicenzo.flightreservation.repos.PassengerRepository;
import com.vicenzo.flightreservation.repos.ReservationRepository;
import com.vicenzo.flightreservation.util.EmailUtil;
import com.vicenzo.flightreservation.util.PDFGenerator;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PDFGenerator pdfGenerator;

    @Autowired
    private EmailUtil emailUtil;

    @Override
    public Reservation bookFlight(ReservationRequest request) {

        // Make payment
        //
        Long flightId = request.getFlightId();
        Flight flight = flightRepository.findById(flightId).get();

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Passenger passenger = mapper.map(request, Passenger.class);

//        Passenger passenger = new Passenger();
//        passenger.setFirstName(request.getPassengerFirstName());
//        passenger.setLastName(request.getPassengerLastName());
//        passenger.setPhone(request.getPassengerPhone());
//        passenger.setEmail(request.getPassengerEmail());

        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        Reservation savedReservation = reservationRepository.save(reservation);

        String filePath = "E:/Udemy/Project/ReservationPDF/reservation" + savedReservation.getId() + ".pdf";
        pdfGenerator.generateItinerary(savedReservation, filePath);

        emailUtil.sendItinerary(passenger.getEmail(),filePath);

        return savedReservation;
    }
}
