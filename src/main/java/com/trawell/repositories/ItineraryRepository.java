package com.trawell.repositories;

import com.trawell.models.Itinerary;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Milione Vincent
 * repository 
 */
public interface ItineraryRepository extends CrudRepository<Itinerary, Long> {

}