package com.trawell.controllers;

import javax.servlet.http.HttpSession;

import com.trawell.models.TrawellGroup;
import com.trawell.models.User;
import com.trawell.services.TrawellGroupService;
import com.trawell.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Milione Vincent
 * @author Lamberti Vincenzo RestUsersController andranno mappate tutte le
 *         funzionalità relative all'itinerario per la comunicazione REST
 *
 */
@RestController
@RequestMapping(value = "/api")
public class RestGroupController {
    @Autowired
    TrawellGroupService daoGroup;
    @Autowired
    UserService daoUser;
    boolean f = false;

    @PostMapping(value = "/group/newGroup", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrawellGroup> addGroup(@RequestBody TrawellGroup group, HttpSession session) {
        User user = (User) session.getAttribute("user");
        TrawellGroup createdGroup = null;

        if (user != null ? !user.getIsAdmin() : f) {
            group.setIdOwner(user.getId());

            createdGroup = daoGroup.create(group);
            createdGroup.getParticipants().add(user);
            daoGroup.update(createdGroup);
            daoUser.update(user);
        }

        return createdGroup == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(createdGroup, HttpStatus.OK);
    }

    @PostMapping(value = "/group/addMember/{username}/{idGroup}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrawellGroup> addMember(@PathVariable(required = true, name = "username") String username,
            @PathVariable(required = true, name = "idGroup") Long idGroup, HttpSession session) {
        User user = (User) session.getAttribute("user");
        TrawellGroup updatedGroup = null;

        if (user != null) {
            User userToAdd = daoUser.findByUsername(username);
            Long idUser = userToAdd.getId();
            TrawellGroup group = daoGroup.findOne(idGroup);

            if (group != null && userToAdd != null
                    ? group.getIdOwner().equals(user.getId()) && user.getId() != idUser && !user.getIsAdmin()
                    : f) {
                user.getUserGroups().add(group);
                group.getParticipants().add(userToAdd);

                updatedGroup = daoGroup.update(group);
            }
        }

        return updatedGroup == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/group/removeMember/{idGroup}/{idUser}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrawellGroup> removeMember(@PathVariable(required = true, name = "idUser") Long idUser,
            @PathVariable(required = true, name = "idGroup") Long idGroup, HttpSession session) {
        User user = (User) session.getAttribute("user");
        TrawellGroup updatedGroup = null;

        if (user != null) {
            User userToRemove = daoUser.findOne(idUser);
            TrawellGroup group = daoGroup.findOne(idGroup);

            if (group != null && userToRemove != null
                    ? group.getIdOwner().equals(user.getId()) && user.getId() != idUser && !user.getIsAdmin()
                    : f) {
                user.getUserGroups().remove(group);
                group.getParticipants().remove(userToRemove);

                updatedGroup = daoGroup.update(group);
            }
        }

        return updatedGroup == null ? new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)
                : new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/group/eliminate/{id}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TrawellGroup> eliminateGroup(@PathVariable(name = "id") Long id, HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (user != null) {
            daoGroup.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}