package com.trawell.controllers;
import javax.servlet.http.HttpSession;

import com.trawell.models.User;
import com.trawell.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Milione Vincent
 * RestUsersController andranno mappate tutte le funzionalità relative all'utente per la comunicazione REST
 *
 */
@RestController
@RequestMapping(value = "/api/users")
public class RestUsersController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.create(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    /**
     * @author Milione Vincent
     * executes an update on all of a user's data
     * @param id user's id on the database
     * @param user new user object containing all the new data
     * @return a JSON object with Http Status 200 if update was successful, 500 otherwise
     */

    @RequestMapping(value = "/users/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user, HttpSession session) {
        System.out.println("hello");
        User u = (User) session.getAttribute("user");
        if (u == null) {

            User updatedUser = null;
            if (user != null && id == user.getId()) {
                updatedUser = userService.update(user); 
            }
            if(updatedUser == null) {
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
             }
            return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
        }

        return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
    }
}