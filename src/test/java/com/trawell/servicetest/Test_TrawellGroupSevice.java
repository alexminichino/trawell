package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
import com.trawell.models.TrawellGroup;
import com.trawell.models.User;
import com.trawell.repositories.JPAGroupRepository;
import com.trawell.services.TrawellGroupService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_TrawellGroupSevice {
    @InjectMocks
    TrawellGroupService dao;

    @Mock
    JPAGroupRepository repo;

    TrawellGroup newGroup;
    TrawellGroup persistedGroup;
    TrawellGroup group;

    @Before public void init () {
        User user = new User ();
        Set<User> set = new HashSet<User>();
        newGroup = new TrawellGroup ();
        group = new TrawellGroup ();
        persistedGroup = new TrawellGroup();

        user.setId(1L);
        set.add(user);
        newGroup.setDescription("description");
        newGroup.setIdItinerary(1L);
        newGroup.setName("gruppo");
        newGroup.setParticipants(set);

        group.setId(1L);
        group.setDescription("description");
        group.setIdItinerary(1L);
        group.setName("gruppo 2");
        group.setParticipants(set);
        
        persistedGroup.setId(1L);
        persistedGroup.setDescription("description");
        persistedGroup.setIdItinerary(1L);
        persistedGroup.setName("gruppo");
        persistedGroup.setParticipants(set);
    }

    @Test
    public void tc_1 () {
        when(repo.save(any(TrawellGroup.class))).thenReturn(persistedGroup);
        assertEquals(Long.valueOf(1L) ,dao.create(newGroup).getId());
    }

    @Test
    public void tc_2 () {
        assertEquals(null, dao.create(persistedGroup));
    }

    @Test
    public void tc_3 () {

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(persistedGroup));
        when(repo.save(any(TrawellGroup.class))).thenReturn(group);

        assertEquals(group ,dao.update(group));
    }

    @Test
    public void tc_4() {
        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());
        assertEquals(null ,dao.update(group));
    }

    @Test
    public void tc_5() {
        ArrayList<TrawellGroup> list = new ArrayList<TrawellGroup>();
        list.add(group);

        when(repo.findAll()).thenReturn(list);
        Collection<TrawellGroup> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(group));
    }

    @Test
    public void tc_6() {
        dao.delete(1L);
        Mockito.verify(repo, times(1)).deleteById(group.getId());
    }
}