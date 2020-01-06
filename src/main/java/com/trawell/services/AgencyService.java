package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.repositories.AdRepository;
import com.trawell.repositories.AgencyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mario Paone
 * AgencyService DAO IMPL
 */
@Service
public class AgencyService implements IAgencyService {

    @Autowired
    private AgencyRepository agencyRepository;

    @Override
    public Collection<Agency> findAll() {
        ArrayList<Agency> agencyDatas = new ArrayList<>();
        agencyRepository.findAll().forEach(agencyDatas::add);
        return agencyDatas;
    }

    @Override
    public Agency findOne(Long id) {
        Optional<Agency> agencyData = agencyRepository.findById(id);
        return agencyData.get();
    }

    @Override
    public Agency create(Agency agency) {
        if (agency.getId() != null) {
            return null;
        }
        Agency savedData = agencyRepository.save(agency);
        return savedData;
    }

    @Override
    public Agency update(Agency agency) {
        Agency dataPersisted = findOne(agency.getId());
        if (dataPersisted == null) {
            //cannot find Agency with specified Id value
            return null;
        }
        Agency updatedData = agencyRepository.save(agency);
        return updatedData;
    }

    @Override
    public void delete(Long id) {
         agencyRepository.delete(findOne(id));
    }

    @Override
    public Agency findById(Long id) {
        return agencyRepository.findById(id).get();
    } 
    
}