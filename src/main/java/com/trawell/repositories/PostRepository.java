package com.trawell.repositories;


import com.trawell.models.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 */
public interface PostRepository extends CrudRepository<Post, Long> {
    
    
}