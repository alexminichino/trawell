package com.trawell.controllers;

import javax.servlet.http.HttpSession;
import com.trawell.models.Carsharing;
import com.trawell.models.User;
import com.trawell.services.CarsharingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class RestCarsharingController {
    @Autowired
    CarsharingService dao;

    @PostMapping(value = "/carsharing/add", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carsharing> add (@RequestBody Carsharing carsharing, HttpSession session){
        User user = (User) session.getAttribute("user");
        Carsharing createdCarsharing = null;

        if (user != null) {
            carsharing.setUser(user);
            user.getUserCreatedAdList().add(carsharing);
            createdCarsharing = dao.create(carsharing);
        }

        return createdCarsharing == null ? new ResponseEntity<Carsharing>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<Carsharing>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "/carsharing/modify", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carsharing> modify (@RequestBody Carsharing carsharing, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Carsharing updatedCarsharing = null;

        if (user != null) {
            carsharing.setUser(user);
            user.getUserCreatedAdList().add(carsharing);
            updatedCarsharing = dao.update(carsharing);
        }

        return updatedCarsharing == null ? new ResponseEntity<Carsharing>(HttpStatus.INTERNAL_SERVER_ERROR) : new ResponseEntity<Carsharing>(updatedCarsharing, HttpStatus.OK);
    }

    @RequestMapping(value = "/carsharing/eliminate/{id}", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Carsharing> delete (HttpSession session, @PathVariable("id") Long id) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            dao.delete(id);
            return new ResponseEntity<Carsharing>(HttpStatus.OK);
        }
        
        return new ResponseEntity<Carsharing>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}