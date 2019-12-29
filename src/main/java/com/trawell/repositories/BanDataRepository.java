package com.trawell.repositories;



import com.trawell.models.BanData;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Mario Paone
 * BanDataRepository
 */
public interface BanDataRepository extends CrudRepository<BanData, Long> {
    
}