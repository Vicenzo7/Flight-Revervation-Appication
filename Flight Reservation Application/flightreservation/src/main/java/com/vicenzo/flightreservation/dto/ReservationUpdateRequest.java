package com.vicenzo.flightreservation.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReservationUpdateRequest {

    private Long id;
    private Boolean checkedIn;
    private int numberOfBags;

}
