package com.trawell.repositories;

import com.trawell.models.TrawellGroup;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Milione Vincent repository
 */
public interface JPAGroupRepository extends JpaRepository<TrawellGroup, Long> {

}