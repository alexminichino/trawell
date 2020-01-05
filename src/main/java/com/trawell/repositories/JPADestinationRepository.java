package com.trawell.repositories;

import com.trawell.models.Destination;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Milione Vincent repository
 */
public interface JPADestinationRepository extends JpaRepository<Destination, Long> {

}