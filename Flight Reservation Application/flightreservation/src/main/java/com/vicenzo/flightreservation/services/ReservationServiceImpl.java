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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
    @Value("${flightreservation.itinerary.dirpath}")
    private  String ITINERARY_DIR ;

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
    @Transactional
    public Reservation bookFlight(ReservationRequest request) {

        LOGGER.info("Inside bookFlight() ");
        // Make payment
        //
        Long flightId = request.getFlightId();
        Flight flight = flightRepository.findById(flightId).get();

        LOGGER.info("Fetching flight for flight id: "+flightId);


        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        Passenger passenger = mapper.map(request, Passenger.class);

//        Passenger passenger = new Passenger();
//        passenger.setFirstName(request.getPassengerFirstName());
//        passenger.setLastName(request.getPassengerLastName());
//        passenger.setPhone(request.getPassengerPhone());
//        passenger.setEmail(request.getPassengerEmail());

        LOGGER.info("Saving the passenger: "+passenger);

        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        LOGGER.info("Saving the reservation: "+reservation);


        Reservation savedReservation = reservationRepository.save(reservation);

        String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";

        LOGGER.info("Generating the Itinerary ");
        pdfGenerator.generateItinerary(savedReservation, filePath);

        LOGGER.info("Emailing the Itinerary ");
        emailUtil.sendItinerary(passenger.getEmail(),filePath);

        return savedReservation;
    }
}
