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
public class RestItineraryControllerTest {

   
    @Mock
    private CarsharingService daocarsharing;
    @Mock
    private ItineraryService daoitinerary;
    @Mock
    private PostService daopost;
    @Mock
    private AdService daoad;

    ArrayList<Itinerary>  itinerarys = new ArrayList<Itinerary>();
    Itinerary modello = new Itinerary();

    @InjectMocks
    RestItineraryController controller = new RestItineraryController();

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
        

        

        Destination destination = new Destination();
        destination.setId(0L);
        destination.setDate(new Date(0,0,0));
        destination.setDescription("description");
        destination.setIsVisited(false);
        destination.setLocation("location");
        destination.setItinerary(modello);

        ArrayList<Destination> destinations = new ArrayList<Destination>();
        ArrayList<Itinerary> userItineraries = new ArrayList<Itinerary>();

        modello.setId(0L);
        modello.setDestinations(destinations);
        userItineraries.add(modello);
        instance.setUserItineraries(userItineraries);
        instance.addItinerary(modello);
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
        
        when(daoitinerary.create(modello)).thenReturn(modello);
        assertEquals(HttpStatus.OK, controller.add(modello, session).getStatusCode());
    }

    @Test
    public void TestModifyNull() {
        
        when(daoitinerary.update(modello)).thenReturn(null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.modify(modello, session).getStatusCode());
    }

    @Test
    public void TestModify() {
        
        when(daoitinerary.update(modello)).thenReturn(modello);
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

    @Test
    public void TestImportItinerary() {
        
        when(daoitinerary.findOne(0L)).thenReturn(modello);
        when(daoitinerary.create(modello)).thenReturn(modello);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.importItinerary(session, 0L).getStatusCode());
    }
    @Test
    public void TestImportItineraryDaoNull() {
        
        when(daoitinerary.findOne(0L)).thenReturn(modello);
        when(daoitinerary.create(modello)).thenReturn(null);
        assertEquals(HttpStatus.OK, controller.importItinerary(session, 0L).getStatusCode());
    }

    @Test
    public void TestimportItineraryNull() {
       
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.importItinerary(session, 0L).getStatusCode());
    }

    @Test
    public void TestpublicizeItinerary() {
        
        when(daoitinerary.findOne(0L)).thenReturn(modello);
        when(daoitinerary.create(modello)).thenReturn(modello);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.publicize(session, 0L).getStatusCode());
    }
    @Test
    public void TestpublicizeDaoNull() {
        
        when(daoitinerary.findOne(0L)).thenReturn(modello);
        when(daoitinerary.create(modello)).thenReturn(null);
        assertEquals(HttpStatus.OK, controller.publicize(session, 0L).getStatusCode());
    }

    @Test
    public void TestpublicizeNull() {
       
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.publicize(session, 0L).getStatusCode());
    }

}