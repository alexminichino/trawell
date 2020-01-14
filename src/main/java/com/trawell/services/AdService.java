package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Ad;
import com.trawell.repositories.AdRepository;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mario Paone
 * AdService DAO IMPL
 */
@Service
public class AdService implements IAdService {

    @Autowired
    private AdRepository adRepository;

    @Override
    public Collection<Ad> findAll() {
        ArrayList<Ad> adDatas = new ArrayList<>();
        adRepository.findAll().forEach(adDatas::add);
        return adDatas;
    }

    @Override
    public Ad findOne(Long id) {
        Optional<Ad> adData = adRepository.findById(id);
        return adData.isPresent() ? adData.get() : null;
    }

    @Override
    public Ad create(Ad ad) {
        if (ad.getId() != null) {
            return null;
        }
        Ad savedData = adRepository.save(ad);
        return savedData;
    }

    @Override
    public Ad update(Ad ad) {
        Ad dataPersisted = findOne(ad.getId());
        if (dataPersisted == null) {
            //cannot find Ad with specified Id value
            return null;
        }
        Ad updatedData = adRepository.save(ad);
        return updatedData;
    }

    @Override
    public void delete(Long id) {
         adRepository.delete(findOne(id));
    }

    
    
}