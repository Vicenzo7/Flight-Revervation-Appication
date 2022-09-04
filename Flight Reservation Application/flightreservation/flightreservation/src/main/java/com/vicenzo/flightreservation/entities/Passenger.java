package com.vicenzo.flightreservation.entities;

import lombok.Data;

import javax.persistence.Entity;


@Data
@Entity
public class Passenger extends AbstractEntity {


    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private String phone;

}
