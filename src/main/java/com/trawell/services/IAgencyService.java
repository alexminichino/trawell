package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Agency;

/**
 * @author Mario Paone
 * IAgencyService
 */

public interface IAgencyService {
    Collection<Agency> findAll();
    Agency findOne(Long id);
    Agency create(Agency agency);
    Agency update(Agency agency);
    void delete(Long id);
}