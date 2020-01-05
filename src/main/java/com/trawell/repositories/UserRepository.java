package com.trawell.repositories;

import com.trawell.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Milione Vincent
 * repository 
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User findByMail(String mail);
}