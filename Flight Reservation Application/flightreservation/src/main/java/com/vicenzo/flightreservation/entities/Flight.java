package com.vicenzo.flightreservation.entities;


import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import java.sql.Timestamp;
import java.util.Date;


@Data
@ToString
@Entity
public class Flight extends AbstractEntity {


    private String flightNumber;
    private String operatingAirlines;
    private String departureCity;
    private String arrivalCity;
    private Date dateOfDeparture;
    private Timestamp estimatedDepartureTime;
}
