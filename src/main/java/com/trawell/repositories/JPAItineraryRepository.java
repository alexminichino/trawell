package com.trawell.repositories;

import com.trawell.models.Itinerary;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Milione Vincent repository
 */
public interface JPAItineraryRepository extends JpaRepository<Itinerary, Long> {

}