package com.trawell.repositories;

import java.util.List;
import com.trawell.models.Complaint;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 */
public interface ComplaintRepository extends CrudRepository<Complaint, Long> {
    
}