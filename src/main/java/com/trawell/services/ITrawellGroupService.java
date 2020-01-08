package com.trawell.services;

import java.util.Collection;

import com.trawell.models.TrawellGroup;

public interface ITrawellGroupService{
    Collection<TrawellGroup> findAll();
    TrawellGroup findOne(Long id);
    TrawellGroup create(TrawellGroup trawellGroup);
    TrawellGroup update(TrawellGroup trawellGroup);
    void delete(Long id);
}