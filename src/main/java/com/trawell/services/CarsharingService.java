package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Carsharing;
import com.trawell.repositories.JPACarsharingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lamberti Vincenzo
 * @author Milione Vincent 
 * provide users of the class the ability to interact with the database and model carsharing ads.
 */
@Service
public class CarsharingService implements ICarsharingService {

    @Autowired
    private JPACarsharingRepository carsharingRepository;

    @Override
    public Collection<Carsharing> findAll() {
        ArrayList<Carsharing> carsharingAds = new ArrayList<Carsharing>();
        carsharingRepository.findAll().forEach(carsharingAds::add);
        return carsharingAds;
    }

    @Override
    public Carsharing findOne(Long id) {
        Optional<Carsharing> ad = carsharingRepository.findById(id);
        return ad.isPresent() ? ad.get() : null;
    }

    @Override
    public Carsharing create(Carsharing carsharing) {
        if (carsharing.getId() != null) {
            //cannot create Carsharing ads with specified Id value
            return null;
        }
        
        return carsharingRepository.save(carsharing);
    }

    @Override
    public Carsharing update(Carsharing carsharing) {
       Carsharing carsharingPersisted = findOne(carsharing.getId());
        if (carsharingPersisted == null) {
            //cannot findcarsharing with specified Id value
            return null;
        }
        
        return carsharingRepository.save(carsharing);
    }

    @Override
    public void delete(Long id) {
        Carsharing carsharingPersisted = findOne(id);
        carsharingPersisted.setUser(null);
        carsharingRepository.save(carsharingPersisted);
        carsharingRepository.delete(findOne(id));
    }

}