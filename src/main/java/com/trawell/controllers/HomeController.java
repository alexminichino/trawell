package com.trawell.controllers;

import com.trawell.services.CarsharingService;
import com.trawell.services.ItineraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Homecontroller: andranno mappate tutte le funzionalità relative ad i controller
 * @author Umberto Russomando
 */

 /**
  * @author Alfieri Davide
  */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CarsharingService daocarsharing;
    @Autowired
    private ItineraryService daoitinerary;
    @GetMapping("/home-page")
    public String landing(Model model) {
        model.addAttribute("carsharingAds", daocarsharing.findAll());
        model.addAttribute("itineraries", daoitinerary.findAll());
        return "pages/home/index";
    }

    
}
