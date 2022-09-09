package com.vicenzo.flightreservation.services;

import com.vicenzo.flightreservation.dto.ReservationRequest;
import com.vicenzo.flightreservation.entities.Reservation;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest reservationRequest);
}
