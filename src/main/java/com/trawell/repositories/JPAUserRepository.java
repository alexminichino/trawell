package com.trawell.repositories;

import com.trawell.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Milione Vincent
 * repository 
 */
public interface JPAUserRepository extends JpaRepository<User, Long> {

}