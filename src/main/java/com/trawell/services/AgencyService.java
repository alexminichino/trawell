package com.trawell.services;
import com.trawell.models.Agency;
import com.trawell.repositories.AgencyRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService DAO IMPL
 */
@Service
public class AgencyService {

    @Autowired
    private AgencyRepository userRepository;


    public Agency create(Agency user) {
        if (user.getId() != null) {
            //cannot create User with specified Id value
            return null;
        }
        Agency savedUser = userRepository.save(user);
        return savedUser;
    }
}