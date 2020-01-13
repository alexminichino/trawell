package com.trawell.repositories;

import java.util.Collection;

import com.trawell.models.Post;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Russomando Umberto
 * PostRepository
 */
public interface PostRepository extends CrudRepository<Post, Long> {
    
    Collection<Post> findByGroupIsNull();

    //@Query("select * from post p where p.is_reported = 1")
    Collection<Post> findByIsReported(Boolean isReported );
}