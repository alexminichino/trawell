package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Photo;

/**
 * @author Mario Paone
 * IPhotoService
 */

public interface IPhotoService {
    Collection<Photo> findAll();
    Photo findOne(Long id);
    Photo create(Photo photo);
    Photo update(Photo photo);
    void delete(Long id);
}