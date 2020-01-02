package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.trawell.models.Destination;
import com.trawell.models.Itinerary;
import com.trawell.repositories.JPADestinationRepository;
import com.trawell.repositories.JPAItineraryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lamberti Vincenzo
 * @author Milione Vincent provides users of the class the ability to interact
 *         with the database and model users.
 */
@Service
public class ItineraryService implements IItineraryService {

    @Autowired
    private JPAItineraryRepository itineraryRepository;

    @Override
    public Collection<Itinerary> findAll() {
        ArrayList<Itinerary> itineraries = new ArrayList<Itinerary>();
        itineraryRepository.findAll().forEach(itineraries::add);
        return itineraries;
    }

    @Override
    public Itinerary findOne(Long id) {
        Optional<Itinerary> ad = itineraryRepository.findById(id);
        return ad.get();
    }

    @Override
    public Itinerary create(Itinerary itinerary) {
        if (itinerary.getId() != null) {
            //cannot create User with specified Id value
            return null;
        }
        return itineraryRepository.save(itinerary);
    }

    @Override
    public Itinerary update(Itinerary itinerary) {
        Itinerary itineraryPersisted = findOne(itinerary.getId());
        if (itineraryPersisted == null) {
            //cannot findcarsharing with specified Id value
            return null;
        }
        itineraryPersisted.getDestinations().clear();
        itinerary.getDestinations();
        List<Destination> list;
     
        return itineraryRepository.save(itinerary);
    }

    @Override
    public void delete(Long id) {
        itineraryRepository.deleteById(id);
    }

}