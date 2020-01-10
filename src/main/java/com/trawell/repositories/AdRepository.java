package com.trawell.repositories;

import com.trawell.models.Ad;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Mario Paone
 * AdRepository
 */
public interface AdRepository extends CrudRepository<Ad, Long> {
    
}