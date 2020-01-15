package com.trawell.Controller;

import static org.junit.Assert.assertEquals;

import com.trawell.models.*;
import com.trawell.services.*;
import com.trawell.controllers.*;
import com.trawell.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Mario Paone 
 * @author Vincenzo Lamberti
 */
@RunWith(MockitoJUnitRunner.class)
public class RestUsersControllerTest {

    @Mock
    private CarsharingService daocarsharing;
    @Mock
    private ItineraryService daoitinerary;
    @Mock
    private PostService daopost;
    @Mock
    private AdService daoad;

    ArrayList<Photo> photo = new ArrayList<Photo>();
    Post modello = new Post();
    ArrayList<Post> posts= new ArrayList<Post>();

    @InjectMocks
    RestUsersController controller = new RestUsersController();

    @Mock
    Model model;

    User instance = new User();
    User instance1= new User();

    MockHttpSession session;

    @Autowired
    private MockMvc mvc;

    @Before
    public void setup() {
        
        instance.setName("Name");
        instance.setBirth(Date.valueOf("1996-12-03"));
        instance.setBanned(false);
        instance.setPhone("Phone");
        instance.setIsAdmin(false);
        instance.setIsBanned(false);
        instance.setMail("Mail");
        instance.setSurname("Surname");
        instance.setUsername("Username");
        instance.setPassword("Password");
        instance.setId(0L);

        instance1.setName("Name");
        instance1.setBirth(Date.valueOf("1996-12-03"));
        instance1.setBanned(false);
        instance1.setPhone("Phone");
        instance1.setIsAdmin(false);
        instance1.setIsBanned(false);
        instance1.setMail("Mail");
        instance1.setSurname("Surname");
        instance1.setUsername("User");
        instance1.setPassword("Password");
        instance1.setId(1L);
        session = new MockHttpSession();
        session.setAttribute("user", instance);
        
        
    }

    @Test
    public void testUpdateUserWhenUserIsNullAndUpdatedUSerIsNull() {
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.updateUser(1L,new User(),"prova",session).getStatusCode());
    }

    @Test
    public void testUpdateUserWhenUserIsNotNUllAndPasswordIsNotEqual(){
        session.setAttribute("user", instance);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, controller.updateUser(0L, instance1  ,"Password", session).getStatusCode());
    }

}