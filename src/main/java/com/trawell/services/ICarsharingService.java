package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Carsharing;


/**
 * @author Milione Vincent
 */

public interface ICarsharingService {
    Collection<Carsharing> findAll();
    Carsharing findOne(Long id);
    Carsharing create(Carsharing carsharing);
    Carsharing update(Carsharing carsharing);
    void delete(Long id);
}