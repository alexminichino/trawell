package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import com.trawell.models.Group;
import com.trawell.repositories.JPAGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lamberti Vincenzo
 * @author Milione Vincent provides users of the class the ability to interact
 *         with the database and model users.
 */
@Service
public class GroupService implements IGroupService {

    @Autowired
    private JPAGroupRepository groupRepository;

    @Override
    public Collection<Group> findAll() {
        ArrayList<Group> groups = new ArrayList<Group>();
        groupRepository.findAll().forEach(groups::add);
        return groups;
    }

    @Override
    public Group findOne(Long id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.get();
    }

    @Override
    public Group create(Group group) {
        if (group.getId() != null) {
            //cannot create User with specified Id value
            return null;
        }
        return groupRepository.save(group);
    }

    @Override
    public Group update(Group group) {
        if (group.getId() == null) {
            //cannot create User with specified Id value
            return null;
        }
        return groupRepository.save(group);
    }

    @Override
    public void delete(Long id) {
        

    }

    
}