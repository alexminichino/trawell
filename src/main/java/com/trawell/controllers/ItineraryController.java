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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
	 * Method checks if the user is already logged
	 * @param session
	 * @return true if he is already logged, false otherwise
	 */
	private boolean isLogged (HttpSession session) {
		return session.getAttribute("user") != null;
	}

    @GetMapping("/create")
    public String create() {
        return "pages/user/itinerary/createitinerary";
    }

    @GetMapping("/view/{id}")
    public String view(HttpSession session, @PathVariable("id") Long id, Model model) {
        if (isLogged(session)) return "pages/home/index";
        //visualizzi il contenuto dell'itinerario da visualizzare
        User user = (User) session.getAttribute("user");
        Itinerary itinerary = new Itinerary(id);
        List<Itinerary> list = user.getUserItineraries();

        if (list == null ? false : list.size() > 0) {

            int index = user.getUserItineraries().indexOf(itinerary);
            Itinerary viewedItinerary = user.getUserItineraries().get(index);

            //accordati 
        }
        
        return "pages/user/itinerary/viewitinerary";
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
            model.addAttribute("isEmpty", true);
        }

        return "pages/user/itinerary/listitinerary";
    }
    
}