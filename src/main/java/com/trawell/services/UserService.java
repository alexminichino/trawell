package com.trawell.services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.User;
import com.trawell.repositories.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService DAO IMPL
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Override
    public User findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }

    @Override
    public User create(User user) {
        if (user.getId() != null) {
            //cannot create User with specified Id value
            return null;
        }
        User savedUser = userRepository.save(user);
        return savedUser;
    }

    @Override
    public User update(User user) {
        User userPersisted = findOne(user.getId());
        if (userPersisted == null) {
            //cannot find User with specified Id value
            return null;
        }
        User updatedUser = userRepository.save(user);
        return updatedUser;
    }

    @Override
    public void delete(Long id) {
         userRepository.delete(findOne(id));
    }
    
}