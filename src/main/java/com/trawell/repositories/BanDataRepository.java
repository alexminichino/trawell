package com.trawell.repositories;

import java.util.List;

import com.trawell.models.BanData;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Mario Paone
 * BanDataRepository
 */
public interface BanDataRepository extends CrudRepository<BanData, Long> {
    
}