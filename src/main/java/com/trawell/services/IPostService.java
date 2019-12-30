package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Post;


/**
 * IUserService
 */

public interface IPostService {
    Collection<Post> findAll();
    Post findOne(Long id);
    Post create(Post User);
    Post update(Post User);
    void delete(Long id);
    
}