package com.vicenzo.flightreservation.controllers;

import com.vicenzo.flightreservation.dto.ReservationUpdateRequest;
import com.vicenzo.flightreservation.entities.Reservation;
import com.vicenzo.flightreservation.repos.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id) {
        return reservationRepository.findById(id).get();
    }

    @PostMapping("/reservations")
    public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
        Reservation reservation = reservationRepository.findById(request.getId()).get();

        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        return reservationRepository.save(reservation);


    }


}
