package com.vicenzo.flightcheckin.integration;

import com.vicenzo.flightcheckin.integration.dto.Reservation;
import com.vicenzo.flightcheckin.integration.dto.ReservationUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ReservationRestClientImpl implements ReservationRestClient {

    @Value("${flightcheckin.reservation.url}")
    private  String RESERVATION_REST_URL;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Reservation findReservation(Long id) {

        Reservation reservation = restTemplate.getForObject(RESERVATION_REST_URL + id, Reservation.class);
        return reservation;
    }

    @Override
    public Reservation updateReservation(ReservationUpdateRequest request) {

        Reservation reservation = restTemplate.postForObject(RESERVATION_REST_URL, request, Reservation.class);
        return reservation;
    }
}
