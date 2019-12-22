package com.trawell.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.trawell.models.Encoder;
import com.trawell.models.User;
import com.trawell.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Milione Vincent
 * RestUsersController andranno mappate tutte le funzionalità relative all'utente per la comunicazione REST
 *
 */
@RestController
@RequestMapping(value = "/users/api")
public class RestUsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.create(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    /**
     * @author Lamberti Vincenzo
     * executes an update on all of a user's data
     * @param id user's id on the database
     * @param user new user object containing all the new data
     * @return a JSON object with Http Status 200 if update was successful, , 500 otherwise
     */
    //sistema i campi che non possono essere vuoti
    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @ModelAttribute User user, @RequestParam (name = "oldpassword", required = true) String old, HttpSession session) {

        User u = (User) session.getAttribute("user");
        String oldPassword = old;

        User updatedUser = null;
        if (user != null && id == u.getId()) {
            
            oldPassword = new Encoder(u.getUsername()).encoding(oldPassword, u.getUsername().length());
            if (oldPassword.equals(u.getPassword())) {
                user.setId(id);
                updatedUser = userService.update(user);
                System.out.println(updatedUser);
            }
            else
                return new ResponseEntity<User>(HttpStatus.NOT_ACCEPTABLE);
        }
        if(updatedUser == null) {
            return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
         }
            return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }
}