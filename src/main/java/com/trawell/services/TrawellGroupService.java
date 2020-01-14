package com.trawell.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.trawell.models.TrawellGroup;
import com.trawell.models.User;
import com.trawell.repositories.JPAGroupRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lamberti Vincenzo
 * @author Milione Vincent provides users of the class the ability to interact
 *         with the database and model users.
 */
@Service
public class TrawellGroupService implements ITrawellGroupService {

    @Autowired
    private JPAGroupRepository groupRepository;

    @Override
    public Collection<TrawellGroup> findAll() {
        ArrayList<TrawellGroup> groups = new ArrayList<TrawellGroup>();
        groupRepository.findAll().forEach(groups::add);
        return groups;
    }

    @Override
    public TrawellGroup findOne(Long id) {
        Optional<TrawellGroup> trawellGroup = groupRepository.findById(id);
        return trawellGroup.isPresent() ? trawellGroup.get() : null;
    }

    @Override
    public TrawellGroup create(TrawellGroup trawellGroup) {
        if (trawellGroup.getId() != null) {
            // cannot create User with specified Id value
            return null;
        }
        return groupRepository.save(trawellGroup);
    }

    @Override
    public TrawellGroup update(TrawellGroup trawellGroup) {
        TrawellGroup groupPersisted = findOne(trawellGroup.getId());
        if (groupPersisted == null) {
            //cannot find Post with specified Id value
            return null;
        }

        return groupRepository.save(trawellGroup);
    }

    @Override
    public void delete(Long id) {

        groupRepository.deleteById(id);

    }

    @Override
    public List<TrawellGroup> findGroupCreatedByUser(User user) {
        if (user == null) {
            return null;
        }
        return groupRepository.findByIdOwner(user.getId());
    }

}