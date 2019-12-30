package com.trawell.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.trawell.models.Itinerary;
import com.trawell.models.User;
import com.trawell.services.ItineraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;



/**
 * @author Milione Vincent
 */
@Controller
@RequestMapping("itinerary")
public class ItineraryController {
    @Autowired
    ItineraryService dao;

    @GetMapping("/create")
    public String create(HttpSession session) {
        return session.getAttribute("user") == null ? "error" : "pages/itinerary/createitinerary";
    }

    @GetMapping("/modify/{id}")
    public String modify (HttpSession session, @PathVariable ("id") Long id, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null ? false : user.getUserItineraries() == null ? false : user.getUserItineraries().size() > 0) {
            int index = user.getUserItineraries().indexOf(new Itinerary(id));
            model.addAttribute("itinerary", user.getUserItineraries().get(index));
        }

        return "pages/itinerary/modifyitinerary";
    }

    @GetMapping("/view/{id}")
    public String view(HttpSession session, @PathVariable("id") Long id, Model model) {
        
        //visualizzi il contenuto dell'itinerario da visualizzare
        User user = (User) session.getAttribute("user");
        Itinerary itinerary = new Itinerary(id);

        if (user == null ? false : user.getUserItineraries() == null ? false : user.getUserItineraries().size() > 0) {
            int index = user.getUserItineraries().indexOf(itinerary);
            model.addAttribute("itinerary", user.getUserItineraries().get(index));
        }
        
        return "pages/itinerary/viewitinerary"; 
    }

    @GetMapping("/list-view")
    public String list(HttpSession session, Model model) {
        //nella pagina html vengono outputtati tutti i nomi degli itinerari... se uno user preme
        //su uno dei nomi allora visualizza il suo contenuto
        User user = (User) session.getAttribute("user");
        List<Itinerary> list = user.getUserItineraries();

        if (list == null ? false : list.size() > 0) {
            //accordati 
            model.addAttribute("isEmpty", true);
        } else {
            user.getUserItineraries();
            model.addAttribute("isEmpty", true);
        }

        return "pages/itinerary/listitinerary";
    }
    
}