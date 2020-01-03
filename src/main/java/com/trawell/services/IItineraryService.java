package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Itinerary;


/**
 * @author Milione Vincent
 * A Database Access Object used for the purpose of manipulating itineraries 
 */

public interface IItineraryService {
    /**
     * @author Milione Vincent
     * The method searches all stored itineraries
     * @return collection of itineraries
     */
    Collection<Itinerary> findAll();
    /**
     * @author Milione Vincent
     * The method searches for a specific itinerary
     * @param id id of the persisted itinerary
     * @return object containing itinerary data
     */
    Itinerary findOne(Long id);
    /**
     * @author Milione Vincent
     * The method saves an itinerary in the persistance layer
     * @param User object containing itinerary data
     * @return the object containing itinerary data and specific id.
     */
    Itinerary create(Itinerary itinerary);
    /**
     * @author Milione Vincent
     * The method saves an updated itinerary in the persistance layer
     * @param User object containing itinerary data 
     * @return object contatining updated itinerary data
     */
    Itinerary update(Itinerary itinerary);
    /**
     * @author Milione Vincent
     * The method permanently removes an itinerary from the persistance layer
     * @param id id of the user stored in the persistance layer
     */
    void delete(Long id);
}