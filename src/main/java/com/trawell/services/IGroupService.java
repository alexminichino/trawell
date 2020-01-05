package com.trawell.services;

import java.util.Collection;

import com.trawell.models.Group;

public interface IGroupService{
    Collection<Group> findAll();
    Group findOne(Long id);
    Group create(Group group);
    Group update(Group group);
    void delete(Long id);
}