package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Itinerary;
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
    private JPAItineraryRepository itinerarRepository;

    @Override
    public Collection<Itinerary> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Itinerary findOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Itinerary create(Itinerary itinerary) {
        if (itinerary.getId() != null) {
            //cannot create User with specified Id value
            return null;
        }
        return itinerarRepository.save(itinerary);
    }

    @Override
    public Itinerary update(Itinerary Itinerary) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub

    }

}