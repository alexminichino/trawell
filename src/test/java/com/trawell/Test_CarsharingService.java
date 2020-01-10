package com.trawell;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;

import com.trawell.models.Carsharing;
import com.trawell.models.User;
import com.trawell.repositories.JPACarsharingRepository;
import com.trawell.services.CarsharingService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        ArrayList<User> list= new ArrayList<User>();
        Date date = new Date(2020, 04, 07);
        Carsharing carsharing= new Carsharing();
        carsharing.setArrival("London");
        carsharing.setDeparture("Paris");
        carsharing.setDepartureDate(date);
        carsharing.setDescription("bla bla bla in realta voglio andare a Narnia");
        carsharing.setUser(user);
        carsharing.setUser_list(list);

        Carsharing car2= new Carsharing();
        car2.setCarsharingspot(5);
        car2.setArrival("London");
        car2.setDeparture("Paris");
        car2.setDepartureDate(date);
        car2.setDescription("bla bla bla in realta voglio andare a Narnia");
        car2.setUser(user);
        car2.setUser_list(list);

        when(repo.save(any(Carsharing.class))).thenReturn(car2);

        assertEquals(Long.valueOf(1L) ,dao.create(carsharing).getId());

    }
    @Test
    public void TC_cs2(){
        User user= new User();
        ArrayList<User> list= new ArrayList<User>();
        Date date = new Date(2020, 04, 07);
        Carsharing carsharing= new Carsharing();
        carsharing.setArrival("London");
        carsharing.setDeparture("Paris");
        carsharing.setDepartureDate(date);
        carsharing.setDescription("bla bla bla in realta voglio andare a Narnia");
        carsharing.setUser(user);
        carsharing.setUser_list(list);

        Carsharing car2= new Carsharing();
        car2.setCarsharingspot(5);
        car2.setArrival("London");
        car2.setDeparture("Paris");
        car2.setDepartureDate(date);
        car2.setDescription("bla bla bla in realta voglio andare a Narnia");
        car2.setUser(user);
        car2.setUser_list(list);

        assertEquals(null ,dao.create(carsharing));
    }
}