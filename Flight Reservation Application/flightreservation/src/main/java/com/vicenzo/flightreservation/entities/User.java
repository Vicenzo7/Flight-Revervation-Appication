package com.vicenzo.flightreservation.entities;

import lombok.Data;

import javax.persistence.Entity;



@Data
@Entity
public class User extends AbstractEntity{



    private String firstName;


    private String lastName;


    private String email;

    private String password;



}
