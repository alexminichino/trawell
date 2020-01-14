package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.BanData;
import com.trawell.repositories.BanDataRepository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mario Paone
 * BanDataService DAO IMPL
 */
@Service
public class BanDataService implements IBanDataService {

    @Autowired
    private BanDataRepository banDataRepository;

    @Override
    public Collection<BanData> findAll() {
        ArrayList<BanData> banDatas = new ArrayList<>();
        banDataRepository.findAll().forEach(banDatas::add);
        return banDatas;
    }

    @Override
    public BanData findOne(Long id) {
        Optional<BanData> banData = banDataRepository.findById(id);
        return banData.isPresent() ? banData.get() : null;
    }

    @Override
    public BanData create(BanData data) {
        if (data.getId() != null) {
            //cannot create BanData with specified Id value
            return null;
        }
        BanData savedData = banDataRepository.save(data);
        return savedData;
    }

    @Override
    public BanData update(BanData data) {
        BanData dataPersisted = findOne(data.getId());
        if (dataPersisted == null) {
            //cannot find BanData with specified Id value
            return null;
        }
        BanData updatedData = banDataRepository.save(data);
        return updatedData;
    }

    @Override
    public void delete(Long id) {
         banDataRepository.delete(findOne(id));
    }

    @Override
    public Collection<BanData> findAllByIdUser(Long idUser){
        ArrayList<BanData> banDatas = new ArrayList<>();
        banDataRepository.findAll().forEach(banDatas::add);
        ArrayList<BanData> banDatasToReturn = new ArrayList<>();
        for (BanData bandata : banDatas){
            if(bandata.getIdUser() == idUser)
                banDatasToReturn.add(bandata);
            
        }
        return banDatasToReturn;

    }
    
}