package com.vicenzo.flightcheckin.controllers;

import com.vicenzo.flightcheckin.integration.ReservationRestClient;
import com.vicenzo.flightcheckin.integration.dto.Reservation;
import com.vicenzo.flightcheckin.integration.dto.ReservationUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CheckInController {

    @Autowired
    private ReservationRestClient reservationRestClient;


    @RequestMapping("/checkInProcess")
    public String checkInProcess(){

        return "startCheckIn";
    }

    @RequestMapping("/showStartCheckin")
    public String showStartCheckin() {
        return "startCheckIn";
    }


    @RequestMapping("/startCheckIn")
    public String startCheckIn(@RequestParam("reservationId") Long reservationId, ModelMap modelMap) {
        System.out.println("inside 2");
        Reservation reservation = reservationRestClient.findReservation(reservationId);
        modelMap.addAttribute("reservation", reservation);
        return "displayReservationDetails";
    }

    @RequestMapping("/completeCheckIn")
    public String completeCheckIn(@RequestParam("reservationId") Long reservationId, @RequestParam("numberOfBags") int numberOfBags) {
        ReservationUpdateRequest request = new ReservationUpdateRequest();
        request.setId(reservationId);
        request.setNumberOfBags(numberOfBags);
        request.setCheckedIn(true);

        reservationRestClient.updateReservation(request);

        return "checkInCorfirmation";
    }

}
