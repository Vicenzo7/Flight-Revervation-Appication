package com.vicenzo.flightreservation.controllers;

import com.vicenzo.flightreservation.dto.ReservationRequest;
import com.vicenzo.flightreservation.entities.Flight;
import com.vicenzo.flightreservation.repos.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    private FlightRepository flightRepository;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId")  Long flightId, ModelMap modelMap) {

        Flight flight = flightRepository.findById(flightId).get();
        modelMap.addAttribute("flight",flight);

        return  "completeReservation";
    }



    @RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
    public String completeReservation(@RequestBody ReservationRequest request){



        return null;
    }


}
