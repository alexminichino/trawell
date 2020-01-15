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
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mock.web.MockHttpSession;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.ui.Model;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;


/**
 * @author Paolo Fasano
 */
@RunWith(MockitoJUnitRunner.class)
public class ItineraryControllerTest {

   
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
    ItineraryController controller = new ItineraryController();

    @Mock
    Model model;

    User instance = new User();

    MockHttpSession session = new MockHttpSession();

    @Autowired
    private MockMvc mvc;

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

        session.setAttribute("user", instance);

       
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @Test
    public void TestCreateNoUser() {
        session.setAttribute("user", null);
        assertEquals("error", controller.create(session));
    }

    @Test
    public void TestCreate() {
        
        assertEquals("pages/itinerary/createitinerary", controller.create(session));
    }

    @Test
    public void TestModify() {
        modello.setId(0L);
        itinerarys.add(modello);
        instance.setUserItineraries(itinerarys);
        session.setAttribute("user", instance);
        assertEquals("pages/itinerary/modifyitinerary", controller.modify(session,0L, model));
    }

    @Test
    public void TestModifyNull() {
        
        session.setAttribute("user", null);
        assertEquals("pages/itinerary/modifyitinerary", controller.modify(session,0L, model));
    }
        
    @Test
    public void TestView() {
        modello.setId(0L);
        itinerarys.add(modello);
        instance.setUserItineraries(itinerarys);
        session.setAttribute("user", instance);
        assertEquals("pages/itinerary/viewitinerary", controller.view(session,0L, model));
    }

    @Test
    public void TestViewNull() {
        
        session.setAttribute("user", null);
        assertEquals("pages/itinerary/viewitinerary", controller.view(session,0L, model));
    }


    @Test
    public void TestList() {
        
        assertEquals("pages/itinerary/list-view", controller.list(session, model));
    }
}