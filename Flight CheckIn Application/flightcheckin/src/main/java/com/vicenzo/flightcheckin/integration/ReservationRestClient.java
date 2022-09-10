package com.vicenzo.flightcheckin.integration;

import com.vicenzo.flightcheckin.integration.dto.Reservation;
import com.vicenzo.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

    public Reservation findReservation(Long id);

    public Reservation updateReservation(ReservationUpdateRequest request);;
}
