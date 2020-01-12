package com.trawell.repositories;


import com.trawell.models.Photo;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Mario Paone
 * PhotoRepository
 */
public interface PhotoRepository extends CrudRepository<Photo, Long> {
    
}