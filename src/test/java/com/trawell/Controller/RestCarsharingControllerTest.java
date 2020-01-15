package com.trawell.Controller;

import static org.junit.Assert.assertEquals;

import com.trawell.models.*;
import com.trawell.services.*;
import com.trawell.controllers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;


import org.springframework.ui.Model;


import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;


/**
 * @author Paolo Fasano
 */
@RunWith(MockitoJUnitRunner.class)
public class RestCarsharingControllerTest {

   
    @Mock
    private CarsharingService daocarsharing;
    @Mock
    private ItineraryService daoitinerary;
    @Mock
    private PostService daopost;
    @Mock
    private AdService daoad;

    ArrayList<Itinerary>  itinerarys = new ArrayList<Itinerary>();
    Carsharing modello = new Carsharing();

    @InjectMocks
    RestCarsharingController controller = new RestCarsharingController();

    @Mock
    Model model;

    User instance = new User();

    MockHttpSession session = new MockHttpSession();


    @Before
    public void setup() {

        System.out.println("Before");
        instance.setName("Name");
        instance.setBirth(new Date(0, 0, 0));
        instance.setBanned(false);
        instance.setPhone("Phone");
        instance.setIsAdmin(false);
        instance.setIsBanned(false);
        instance.setMail("Mail");
        instance.setSurname("Surname");
        instance.setUsername("Username");
        instance.setPassword("Password");
        instance.setId(0L);

        modello.setId(0L);
        modello.setArrival("arrival");
        modello.setCarsharingspot(1);
        modello.setDepartureDate(new Date(0,0,0));
        modello.setDescription("description");
              
        ArrayList<Carsharing> userCreatedAddList = new ArrayList<Carsharing>();
        userCreatedAddList.add(modello);
        instance.setUserCreatedAdList(userCreatedAddList);
        
        
        
        session.setAttribute("user", instance);
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @Test
    public void TestAddNull() {
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.add(modello, session).getStatusCode());
    }

    
    @Test
    public void TestAdd() {
        
        when(daocarsharing.create(modello)).thenReturn(modello);
        assertEquals(HttpStatus.OK, controller.add(modello, session).getStatusCode());
    }

    @Test
    public void TestModifyNull() {
        
        when(daocarsharing.update(modello)).thenReturn(null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.modify(modello, session).getStatusCode());
    }

    @Test
    public void TestModify() {
        
        when(daocarsharing.update(modello)).thenReturn(modello);
        assertEquals(HttpStatus.OK, controller.modify(modello, session).getStatusCode());
    }

    @Test
    public void TestDelete() {
        
        //when(daoitinerary.update(modello)).thenReturn(modello);
        assertEquals(HttpStatus.OK, controller.delete(session, 0L).getStatusCode());
    }

    @Test
    public void TestDeleteNull() {
       
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.delete(session, 0L).getStatusCode());
    }

    

}