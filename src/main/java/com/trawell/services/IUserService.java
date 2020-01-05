package com.trawell.services;

import java.util.Collection;

import com.trawell.models.User;


/**
 * @author Alfieri Davide
 * A Database Access Object used for the purpose of manipulating the 
 * data of users signed up on the platform
 */

public interface IUserService {
    /**
     * @author Alfieri Davide
     * The method searches all users that are currently signed up on the platform
     * @return collection of users signed up to the platform
     */
    Collection<User> findAll();
    /**
     * @author Alfieri Davide
     * The method searches for a specific user amongst the ones currently signed up
     * @param id id of the persisted user
     * @return object containing user data
     */
    User findOne(Long id);
    /**
     * @author Alfieri Davide
     * The method searches a user with a specific username
     * @param username username of the user
     * @return object containing user data
     */
    User findByUsername(String username);
    /**
     * @author Alfieri Davide
     * The method saves new user's info in the persistance layer
     * @param User object containing user data
     * @return the object containing user data and specific id.
     */
    User create(User User);
    /**
     * @author Alfieri Davide
     * The method saves the user's updated info in the persistance layer
     * @param User object containing user data
     * @return the object containing the updated user data.
     */
    User update(User User);
    /**
     * @author Alfieri Davide
     * The method permanently removes a user from the persistance layer
     * @param id id of the user stored in the persistance layer
     */
    void delete(Long id);

    /**
     * 
     * @param username
     * @return
     */
    public boolean doesUsernameExist(String username);

    /**
     * 
     * @param email
     * @return
     */
    public boolean doesEmailExist(String email);
}