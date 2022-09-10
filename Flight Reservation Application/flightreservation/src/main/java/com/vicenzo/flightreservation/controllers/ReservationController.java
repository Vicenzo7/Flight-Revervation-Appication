package com.vicenzo.flightreservation.controllers;

import com.vicenzo.flightreservation.dto.ReservationRequest;
import com.vicenzo.flightreservation.entities.Flight;
import com.vicenzo.flightreservation.entities.Reservation;
import com.vicenzo.flightreservation.repos.FlightRepository;
import com.vicenzo.flightreservation.services.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId")  Long flightId, ModelMap modelMap) {
        LOGGER.info("Inside showCompleteReservation() invoked with the flight Id: "+flightId);
        Flight flight = flightRepository.findById(flightId).get();
        modelMap.addAttribute("flight",flight);
        LOGGER.info("Flight is "+flight);
        return  "completeReservation";
    }



    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(ReservationRequest request, ModelMap modelMap){
        LOGGER.info("Inside completeReservation()"+request);

        System.out.println("in the method");
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg","Reservation created successfully and the id is "+reservation.getId());

        return "reservationConfirmation";
    }


}
