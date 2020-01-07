package com.trawell.repositories;

import com.trawell.models.Carsharing;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Milione Vincent repository
 */
public interface JPACarsharingRepository extends JpaRepository<Carsharing, Long> {

}