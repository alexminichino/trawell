package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Ad;

/**
 * @author Mario Paone
 * IAdService
 */

public interface IAdService {
    Collection<Ad> findAll();
    Ad findOne(Long id);
    Ad create(Ad ad);
    Ad update(Ad ad);
    void delete(Long id);
}