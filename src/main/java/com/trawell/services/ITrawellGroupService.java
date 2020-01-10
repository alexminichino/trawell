package com.trawell.services;

import java.util.Collection;
import java.util.List;

import com.trawell.models.TrawellGroup;
import com.trawell.models.User;

public interface ITrawellGroupService{
    List<TrawellGroup> findGroupCreatedByUser(User user);
    Collection<TrawellGroup> findAll();
    TrawellGroup findOne(Long id);
    TrawellGroup create(TrawellGroup trawellGroup);
    TrawellGroup update(TrawellGroup trawellGroup);
    void delete(Long id);
}