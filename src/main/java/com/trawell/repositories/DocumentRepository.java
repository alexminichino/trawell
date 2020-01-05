package com.trawell.repositories;


import com.trawell.models.Document;
import org.springframework.data.repository.CrudRepository;

/**
 * DocumentRepository
 */
public interface DocumentRepository extends CrudRepository<Document, Long> {
    
    
}