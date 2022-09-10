package com.vicenzo.flightcheckin.integration.dto;


import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;


@Data
public class Flight {

    private Long id;
    private String flightNumber;
    private String operatingAirlines;
    private String departureCity;
    private String arrivalCity;
    private Date dateOfDeparture;
    private Timestamp estimatedDepartureTime;
}
