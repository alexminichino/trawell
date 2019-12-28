package com.trawell.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.trawell.models.Itinerary;
import com.trawell.models.User;
import com.trawell.services.ItineraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author Milione Vincent
 */
@Controller
@RequestMapping("users/itinerary")
public class ItineraryController {
    @Autowired
    ItineraryService dao;

    @GetMapping("/create")
    public String create() {
        return "pages/user/itinerary/createitinerary";
    }

    @PostMapping("/add")
    public String add (@ModelAttribute Itinerary itinerary, HttpSession session){
        User user = (User) session.getAttribute("user");
        user.addItinerary(itinerary);
        dao.create(itinerary);
        return "pages/user/home";
    }

    @GetMapping("/view")
    public String view(HttpSession session) {
        //visualizzi il contenuto degli itinerari
        return "pages/user/itinerary/viewitinerary";
    }

    @GetMapping("/list-view")
    public String list() {
        //nella pagina html vengono outputtati tutti i nomi degli itinerari... se uno user preme
        //su uno dei nomi allora visualizza il suo contenuto
        return "pages/user/itinerary/listitinerary";
    }
    
}