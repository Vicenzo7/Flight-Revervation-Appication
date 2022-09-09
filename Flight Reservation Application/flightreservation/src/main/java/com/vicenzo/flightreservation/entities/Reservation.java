package com.vicenzo.flightreservation.entities;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Entity
@Data
public class Reservation extends  AbstractEntity{


    private boolean checkedIn;
    private int numberOfBags;

    @OneToOne
    private  Passenger passenger;

    @OneToOne
    private Flight flight;

}
