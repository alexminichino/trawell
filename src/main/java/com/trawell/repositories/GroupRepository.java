package com.trawell.repositories;

import com.trawell.models.TrawellGroup;

import org.springframework.data.repository.CrudRepository;

/**
 * GroupRepository
 */
public interface GroupRepository extends CrudRepository<TrawellGroup, Long> {
}