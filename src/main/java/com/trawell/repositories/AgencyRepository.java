package com.trawell.repositories;
import com.trawell.models.Agency;

import org.springframework.data.repository.CrudRepository;

/**
 * @author Mario Paone
 * AgencyRepository
 */
public interface AgencyRepository extends CrudRepository<Agency, Long> {
    
}