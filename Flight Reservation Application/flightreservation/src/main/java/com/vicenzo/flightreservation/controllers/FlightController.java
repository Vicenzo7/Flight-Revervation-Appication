package com.vicenzo.flightreservation.controllers;

import com.vicenzo.flightreservation.entities.Flight;
import com.vicenzo.flightreservation.repos.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);

    @Autowired
    private FlightRepository flightRepository;

    @RequestMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to, @RequestParam("departureDate")
            @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate , ModelMap modelMap){

        LOGGER.info("Inside findFlights() From: "+from +" TO: "+to+" Departure Date: "+departureDate);

        List<Flight> flights = flightRepository.findFlights(from, to, departureDate);

        modelMap.addAttribute("flights",flights);

        LOGGER.info("FLights found are :" +flights);

        return "displayFlights";
    }



    @RequestMapping("admin/showAddFlight")
    public String showAddFlight(){
        LOGGER.info("Inside  showAddFlight()");
        return "addFlight";
    }


}
