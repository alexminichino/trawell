package com.trawell;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import com.trawell.models.Destination;
import com.trawell.models.Itinerary;
import com.trawell.models.User;
import com.trawell.repositories.JPAItineraryRepository;
import com.trawell.services.ItineraryService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        ArrayList<Destination> vet = new ArrayList<Destination>();
        Itinerary itinerary= new Itinerary();
        itinerary.setName("Mario");
        itinerary.setUser(user);
        itinerary.setDestinations(vet);

        Itinerary succes= new Itinerary();
        succes.setId(1L);
        succes.setName("Mario");
        succes.setUser(user);
        succes.setDestinations(vet);
        
        when(repo.save(any(Itinerary.class))).thenReturn(succes);

        assertEquals(Long.valueOf(1L) ,dao.create(itinerary).getId());
    }
    @Test
    public void TC_it2(){
        User user= new User();
        ArrayList<Destination> vet = new ArrayList<Destination>();
        Itinerary itinerary= new Itinerary();
        itinerary.setName("Mario");
        itinerary.setUser(user);
        itinerary.setDestinations(vet);

        Itinerary succes= new Itinerary();
        succes.setId(1L);
        succes.setName("Mario");
        succes.setUser(user);
        succes.setDestinations(vet);

        assertEquals(null ,dao.create(itinerary));
    }

}