package com.vicenzo.flightreservation.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;



@Data
@Entity
public class User extends AbstractEntity{



    private String firstName;


    private String lastName;


    private String email;

    private String password;



}
