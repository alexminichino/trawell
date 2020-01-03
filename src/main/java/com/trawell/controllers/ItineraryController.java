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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * @author Milione Vincent
 */
@Controller
@RequestMapping("itinerary")
public class ItineraryController {
    @Autowired
    ItineraryService dao;

   /**
     * @author Milione Vincent
     * The method handles "/itinerary/create" get request and maps it to the corresponding page.
     * If user is not logged, unlogged user gets sent to error page
     * @param session
     * @return url of the page used to create an itinerary
     */
    @GetMapping("/create")
    public String create(HttpSession session) {
        return session.getAttribute("user") == null ? "error" : "pages/itinerary/createitinerary";
    }

    /**
     * The method handles "/itinerary/change?id = {id}" get request and maps it to the corresponding page
     * The url is invoked when user wants to change a specific itinerary that he owns.
     * If user is not logged, unlogged user gets sent to error page
     * @author Milione Vincent
     * @param session
     * @param id id of the corresponding car sharing that user wants to update
     * @param model 
     * @return url of the page used to modify the content of a itinerary
     */
    @GetMapping("/modify")
    public String modify (HttpSession session, @RequestParam("id") Long id, Model model) {
        User user = (User) session.getAttribute("user");

        if (user == null & id == null ? false : user.getUserItineraries() == null ? false : user.getUserItineraries().size() > 0) {
            int index = user.getUserItineraries().indexOf(new Itinerary(id));
            model.addAttribute("itinerary", user.getUserItineraries().get(index));
        }

        return "pages/itinerary/modifyitinerary";
    }

    /**
     * The method handles "/itinerary/view?id = {id}" get request and maps it to the corresponding page.
     * The url is invoked when user wants to view the contents of a specific itinerary that he owns.
     * If user is not logged, unlogged user gets sent to error page
     * @author Milione Vincent
     * @param session
     * @param id id of the itinerary the user wants to view
     * @param model
     * @return url of the view used to view the content of a itinerary
     */
    @GetMapping("/view")
    public String view(HttpSession session, @RequestParam("id") Long id, Model model) {
        
        //visualizzi il contenuto dell'itinerario da visualizzare
        User user = (User) session.getAttribute("user");
        Itinerary itinerary = new Itinerary(id);

        if (user == null && id == null ? false : user.getUserItineraries() == null ? false : user.getUserItineraries().size() > 0) {
            int index = user.getUserItineraries().indexOf(itinerary);
            model.addAttribute("itinerary", user.getUserItineraries().get(index));
        }
        
        return "pages/itinerary/viewitinerary"; 
    }

    /**
     * The method handles "/itinerary/list-view" get request and maps it to the corresponding page.
     * The url is invoked when user wants to view a generic list of all the itinerary he owns.
     * If user is not logged, unlogged user gets sent to error page.
     * @author Milione Vincent
     * @param session
     * @param model
     * @return url of the view used to view displaying the list
     */
    @GetMapping("/list-view")
    public String list(HttpSession session, Model model) {
        //nella pagina html vengono outputtati tutti i nomi degli itinerari... se uno user preme
        //su uno dei nomi allora visualizza il suo contenuto
        User user = (User) session.getAttribute("user");
        List<Itinerary> list = user.getUserItineraries();

        if (list == null ? false : list.size() <= 0) {
            //accordati 
            model.addAttribute("isEmpty", true);
        } else {
            model.addAttribute("itineraries", user.getUserItineraries());
            model.addAttribute("isEmpty", false);
        }

        return "pages/itinerary/list-view";
    }
    
}