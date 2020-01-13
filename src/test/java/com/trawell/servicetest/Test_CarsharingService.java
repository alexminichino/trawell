package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import com.trawell.models.Carsharing;
import com.trawell.models.User;
import com.trawell.repositories.JPACarsharingRepository;
import com.trawell.services.CarsharingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_CarsharingService {
    @InjectMocks
    CarsharingService dao;
    
    @Mock
    JPACarsharingRepository repo;

    @Test
    public void TC_cs1(){
        User user= new User();
        Date date = new Date(2020, 04, 07);
        Carsharing carsharing= new Carsharing();
        carsharing.setArrival("London");
        carsharing.setDeparture("Paris");
        carsharing.setDepartureDate(date);
        carsharing.setDescription("bla bla bla in realta voglio andare a Narnia");
        carsharing.setUser(user);
        

        Carsharing car2= new Carsharing();
        car2.setId(1L);
        car2.setCarsharingspot(5);
        car2.setArrival("London");
        car2.setDeparture("Paris");
        car2.setDepartureDate(date);
        car2.setDescription("bla bla bla in realta voglio andare a Narnia");
        car2.setUser(user);
        
        when(repo.save(any(Carsharing.class))).thenReturn(car2);
        assertEquals(Long.valueOf(1L) ,dao.create(carsharing).getId());

    }

    @Test
    public void TC_cs2(){
        User user= new User();
        Date date = new Date(2020, 04, 07);
        Carsharing carsharing= new Carsharing();
        carsharing.setId(1L);
        carsharing.setCarsharingspot(5);
        carsharing.setArrival("London");
        carsharing.setDeparture("Paris");
        carsharing.setDepartureDate(date);
        carsharing.setDescription("bla bla bla in realtà voglio andare a Narnia");
        carsharing.setUser(user);

        assertEquals(null ,dao.create(carsharing));
    }

    @Test
    public void TC_cs3(){
        User user= new User();
        Date date = new Date(2020, 04, 11);
        Carsharing upcarsharing= new Carsharing();
        upcarsharing.setId(1L);
        upcarsharing.setCarsharingspot(4);
        upcarsharing.setArrival("London");
        upcarsharing.setDeparture("Paris");
        upcarsharing.setDepartureDate(date);
        upcarsharing.setDescription("bla bla bla in realtà voglio andare al mare ... ma il mare dov'è?!");
        upcarsharing.setUser(user);

        Date date1 = new Date(2020, 04, 07);
        Carsharing persistCarsharing= new Carsharing();
        upcarsharing.setId(1L);
        upcarsharing.setCarsharingspot(5);
        upcarsharing.setArrival("London");
        upcarsharing.setDeparture("Paris");
        upcarsharing.setDepartureDate(date1);
        upcarsharing.setDescription("bla bla bla in realtà voglio andare a Narnia");
        upcarsharing.setUser(user);

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(persistCarsharing));
        when(repo.save(any(Carsharing.class))).thenReturn(upcarsharing);
        assertEquals(upcarsharing ,dao.update(upcarsharing));
    }

    @Test
    public void TC_cs4(){
        User nuser= new User();
        Date date = new Date(2020, 03, 20);
        Carsharing upCarsharing = new Carsharing();
        upCarsharing.setId(3L);
        upCarsharing.setArrival("Frosinone");
        upCarsharing.setCarsharingspot(7);
        upCarsharing.setDeparture("Milano");
        upCarsharing.setDepartureDate(date);
        upCarsharing.setDescription("bla bla bla in realtà li lascio su una piazzola e me vado");
        upCarsharing.setUser(nuser);

        when(repo.findById(Long.valueOf(3L))).thenReturn(Optional.empty());

        assertEquals(null ,dao.update(upCarsharing));
    }

    @Test
    public void TC_cs5(){
        Carsharing findCarsharing = new Carsharing();
        Date date = new Date(2020, 07, 04);
        User user = new User();
        ArrayList<Carsharing> c= new ArrayList<>();
        findCarsharing.setId(1L);
        findCarsharing.setArrival("Nuova Dheli");
        findCarsharing.setCarsharingspot(4);
        findCarsharing.setDeparture("Dubai");
        findCarsharing.setDepartureDate(date);
        findCarsharing.setDescription("bla bla bla in realta voglio pagare con soldi di cioccolata ");
        findCarsharing.setUser(user);
        c.add(findCarsharing);

        when(repo.findAll()).thenReturn(c);
        Collection<Carsharing> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(findCarsharing));
    }
    
    @Test
    public void TC_cs6(){
        Carsharing rCarsharing= new Carsharing();
        Date d= new Date(2020, 04, 22);
        User user = new User();
        rCarsharing.setId(2l);
        rCarsharing.setArrival("Salerno");
        rCarsharing.setCarsharingspot(3);
        rCarsharing.setDeparture("Bari");
        rCarsharing.setDepartureDate(d);
        rCarsharing.setDescription("bla bla bla in realtà Davide ci lascia a piedi");
        rCarsharing.setUser(user);

        when(repo.findById(2L)).thenReturn(Optional.ofNullable(rCarsharing));
        dao.delete(2l);
        Mockito.verify(repo, times(1)).delete(rCarsharing);
    }
}