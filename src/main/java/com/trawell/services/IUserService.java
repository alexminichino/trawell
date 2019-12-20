package com.trawell.services;

import java.util.Collection;

import com.trawell.models.User;


/**
 * IUserService
 */

public interface IUserService {
    Collection<User> findAll();
    User findOne(Long id);
    User findByUsername(String username);
    User create(User User);
    User update(User User);
    void delete(Long id);
}