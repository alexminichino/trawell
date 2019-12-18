package com.trawell.repositories;

import java.util.List;
import com.trawell.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}