package com.trawell.services;

import java.util.Collection;

import com.trawell.models.User;

import org.springframework.stereotype.Service;

/**
 * IUserService
 */

public interface IUserService {
    Collection<User> findAll();
    User findOne(Long id);
    User create(User User);
    User update(User User);
    void delete(Long id);
}