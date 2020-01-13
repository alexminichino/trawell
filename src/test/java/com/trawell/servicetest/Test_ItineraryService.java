package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import com.trawell.models.Destination;
import com.trawell.models.Itinerary;
import com.trawell.models.User;
import com.trawell.repositories.JPAItineraryRepository;
import com.trawell.services.ItineraryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_ItineraryService{
    @InjectMocks
    ItineraryService dao;
    
    @Mock
    JPAItineraryRepository repo;

    @Test
    public void TC_it (){
        User user= new User();
        
        Itinerary itinerary= new Itinerary();
        itinerary.setName("Mario");
        itinerary.setUser(user);

        Itinerary succes= new Itinerary();
        succes.setId(1L);
        succes.setName("Mario");
        succes.setUser(user);
        
        
        when(repo.save(any(Itinerary.class))).thenReturn(succes);

        assertEquals(Long.valueOf(1L) ,dao.create(itinerary).getId());
    }
    @Test
    public void TC_it2(){
        User user= new User();
        Itinerary itinerary= new Itinerary();
        itinerary.setId(1L);
        itinerary.setName("Mario");
        itinerary.setUser(user);
        
        Itinerary itic= new Itinerary();
        itic.setId(1L);
        itic.setName("Mario");
        itic.setUser(user);

        assertEquals(null ,dao.create(itinerary));
    }

    @Test
    public void TC_it3(){
        User user= new User();
        ArrayList <Destination> dest= new ArrayList<Destination>();
        Itinerary upitinerary= new Itinerary();
        Date date1 = new Date(2020, 04, 07);
        Destination d = new Destination();
        d.setLocation("Berlino");
        d.setDate(date1);
        d.setId(1L);
        d.setDescription("bla bla ");
        d.setIsVisited(true);
        dest.add(d);
        upitinerary.setId(1L);
        upitinerary.setName("Silvio");
        upitinerary.setUser(user);
        upitinerary.setDestinations(dest);

        ArrayList <Destination> destinations = new ArrayList<Destination>();
        Itinerary persistItinerary = new Itinerary();
        Date date = new Date(2020, 04, 11);
        Destination e= new Destination();
        e.setLocation("Londra");
        e.setDate(date);
        e.setDescription("bla");
        e.setId(1L);
        e.setIsVisited(true);
        destinations.add(e);
        persistItinerary.setId(1L);
        persistItinerary.setName("Mario");
        persistItinerary.setUser(user);
        persistItinerary.setDestinations(destinations);

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(upitinerary));
        when(repo.save(any(Itinerary.class))).thenReturn(persistItinerary);
        assertEquals(upitinerary ,dao.update(upitinerary));

    }

    @Test
    public void TC_it4(){
        User user= new User();
        Date date = new Date(2020, 9, 22);
        Destination d= new Destination();
        ArrayList<Destination> dest= new ArrayList<>();
        Itinerary upItinerary= new Itinerary();
        upItinerary.setId(1L);
        upItinerary.setName("Davide");
        upItinerary.setUser(user);
        d.setId(1L);
        d.setDescription("bla bla");
        d.setIsVisited(true);
        d.setLocation("Berlino");
        d.setDate(date);
        dest.add(d);

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());

        assertEquals(null ,dao.update(upItinerary));
    }

    @Test
    public void TC_it5(){
        User user= new User();
        Date date = new Date(2020, 9, 22);
        Destination d= new Destination();
        ArrayList<Destination> dest= new ArrayList<Destination>();
        ArrayList<Itinerary> list = new ArrayList<Itinerary>();
        Itinerary itinerary= new Itinerary();
        itinerary.setId(1L);
        itinerary.setName("Davide");
        itinerary.setUser(user);
        d.setId(1L);
        d.setDescription("bla bla");
        d.setIsVisited(true);
        d.setLocation("Berlino");
        d.setDate(date);
        dest.add(d);
        list.add(itinerary);
        
        when(repo.findAll()).thenReturn(list);
        Collection<Itinerary> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(itinerary));
    }
/*
    @Test
    public void TC_it6 () {
        User user= new User();
        Date date = new Date(2020, 9, 22);
        Destination d= new Destination();
        ArrayList<Destination> dest= new ArrayList<Destination>();
        Itinerary itinerary= new Itinerary();
        itinerary.setId(1L);
        itinerary.setName("Davide");
        itinerary.setUser(user);
        d.setId(1L);
        d.setDescription("bla bla");
        d.setIsVisited(true);
        d.setLocation("Berlino");
        d.setDate(date);
        dest.add(d);
        itinerary.setDestinations(dest);

        ArrayList <Destination> destinations= new ArrayList<Destination>();
        Itinerary upitinerary= new Itinerary();
        destinations.add(d);
        upitinerary.setId(1L);
        upitinerary.setName("Davide");
        upitinerary.setUser(null);
        upitinerary.setDestinations(destinations);

        when(repo.findById(1L)).thenReturn(Optional.of(itinerary));
        when(repo.findById(1L)).thenReturn(Optional.of(itinerary));
        when(repo.save(any(Itinerary.class))).thenReturn(upitinerary);

        dao.delete(1L);
        Mockito.verify(repo, times(1)).delete(itinerary);
    }*/
}