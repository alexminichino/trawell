package com.trawell.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.trawell.models.Destination;
import com.trawell.models.Itinerary;
import com.trawell.models.User;
import com.trawell.services.ItineraryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Milione Vincent
 * @author Lamberti Vincenzo
 * RestUsersController andranno mappate tutte le funzionalità relative all'utente per la comunicazione REST
 *
 */
@RestController
@RequestMapping(value = "/api")
public class RestItineraryController {
    @Autowired
    ItineraryService dao;

    @PostMapping(value = "/itinerary/add", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Itinerary> add (@RequestBody Itinerary itinerary, HttpSession session){
        User user = (User) session.getAttribute("user");
        Itinerary createdItinerary = null;

        if (user != null) {
            user.addItinerary(itinerary);
            System.out.println(itinerary.getDestinations());
            itinerary.getDestinations().parallelStream().forEach(d -> {d.setItinerary(itinerary);});
            createdItinerary = dao.create(itinerary);
        }

        return createdItinerary == null ? new ResponseEntity<Itinerary>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<Itinerary>(createdItinerary, HttpStatus.OK);
    }
    
    @GetMapping(value = "/itinerary/prova", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Itinerary> prova (){
        List<Destination> list = new java.util.ArrayList<Destination> ();
        Destination d = new Destination();
        
        d.setDate(new java.util.Date());
        d.setDescription("description");
        d.setLocation("location");

        list.add(d);
        Itinerary itinerary = new Itinerary("owo", list);

        return new ResponseEntity<Itinerary>(itinerary, HttpStatus.OK);
    }

    @RequestMapping(value = "/itinerary/modify", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Itinerary> modify (@RequestBody Itinerary itinerary,@PathVariable("id") Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Itinerary updatedItinerary = null;

        if (user != null) {
            itinerary.setUser(user);
            updatedItinerary = dao.update(itinerary);
        }

        return updatedItinerary == null ? new ResponseEntity<Itinerary>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<Itinerary>(updatedItinerary, HttpStatus.OK);
    }

    @RequestMapping(value = "/itinerary/eliminate/{id}", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Itinerary> delete (HttpSession session, @PathVariable("id") Long id) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            dao.delete(id);
            return new ResponseEntity<Itinerary>(HttpStatus.OK);
        }
        
        return new ResponseEntity<Itinerary>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}