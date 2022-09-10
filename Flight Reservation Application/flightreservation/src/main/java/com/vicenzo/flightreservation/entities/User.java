package com.vicenzo.flightreservation.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;



@Data
@ToString
@Entity
public class User extends AbstractEntity{



    private String firstName;


    private String lastName;


    private String email;

    private String password;



}
