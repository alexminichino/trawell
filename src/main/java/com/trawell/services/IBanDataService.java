package com.trawell.services;

import java.util.Collection;

import com.trawell.models.BanData;

/**
 * @author Mario Paone
 * IBanDataService
 */

public interface IBanDataService {
    Collection<BanData> findAll();
    BanData findOne(Long id);
    BanData create(BanData User);
    BanData update(BanData User);
    void delete(Long id);
    Collection<BanData> findAllByIdUser(Long idUser);
}