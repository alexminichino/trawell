package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Photo;
import com.trawell.repositories.PhotoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mario Paone
 * PhotoService DAO IMPL
 */
@Service
public class PhotoService implements IPhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public Collection<Photo> findAll() {
        ArrayList<Photo> photoDatas = new ArrayList<>();
        photoRepository.findAll().forEach(photoDatas::add);
        return photoDatas;
    }

    @Override
    public Photo findOne(Long id) {
        Optional<Photo> photoData = photoRepository.findById(id);
        return photoData.get();
    }

    @Override
    public Photo create(Photo photo) {
        if (photo.getId() != null) {
            return null;
        }
        Photo savedData = photoRepository.save(photo);
        return savedData;
    }

    @Override
    public Photo update(Photo photo) {
        Photo dataPersisted = findOne(photo.getId());
        if (dataPersisted == null) {
            //cannot find Photo with specified Id value
            return null;
        }
        Photo updatedData = photoRepository.save(photo);
        return updatedData;
    }

    @Override
    public void delete(Long id) {
         photoRepository.delete(findOne(id));
    }

    
    
}