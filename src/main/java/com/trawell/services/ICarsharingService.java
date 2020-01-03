package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Carsharing;


/**
 * @author Milione Vincent
 * A Database Access Object used for the purpose of manipulating carsharing ads
 */

public interface ICarsharingService {
    /**
     * @author Milione Vincent
     * The method searches all stored carsharing ads
     * @return collection of carsharing ads
     */
    Collection<Carsharing> findAll();
    /**
     * @author Milione Vincent
     * The method searches for a specific carsharing ad
     * @param id id of the persisted carsharing ad
     * @return object containing carsharing ad data
     */
    Carsharing findOne(Long id);
    /**
     * @author Milione Vincent
     * The method saves an carsharing ad in the persistance layer
     * @param User object containing carsharing ad data
     * @return the object containing carsharing ad data and specific id.
     */
    Carsharing create(Carsharing carsharing);
    /**
     * @author Milione Vincent
     * The method saves an updated carsharing ad in the persistance layer
     * @param User object containing carsharing ad data 
     * @return object contatining updated carsharing ad data
     */
    Carsharing update(Carsharing carsharing);
    /**
     * @author Milione Vincent
     * The method permanently removes an carsharing ad from the persistance layer
     * @param id id of the user stored in the persistance layer
     */
    void delete(Long id);
}