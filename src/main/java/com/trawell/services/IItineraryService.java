package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Itinerary;


/**
 * @author Milione Vincent
 */

public interface IItineraryService {
    Collection<Itinerary> findAll();
    Itinerary findOne(Long id);
    Itinerary create(Itinerary Itinerary);
    Itinerary update(Itinerary Itinerary);
    void delete(Long id);
}