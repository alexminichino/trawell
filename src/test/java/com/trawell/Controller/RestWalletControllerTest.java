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
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Vincenzo Lamberti
 */
@RunWith(MockitoJUnitRunner.class)
public class RestWalletControllerTest{

    @Mock
    private UserService daouse;
    @Mock
    private CarsharingService daocarsharing;
    @Mock
    private ItineraryService daoitinerary;
    @Mock
    private WalletService daowall;
    @Mock
    private AdService daoad;
    @Mock
    private DocumentService daodoc;

    ArrayList<Photo> photo = new ArrayList<Photo>();
    Post modello = new Post();
    ArrayList<Post> posts= new ArrayList<Post>();

    @InjectMocks
    RestWalletController controller = new RestWalletController();

    @Mock
    Model model;

    User instance = new User();
    Wallet instance1= new Wallet();
    Document doc= new Document();

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

        instance1.setId(1L);
        instance1.setUser(instance);
        instance1.setPrivate(true);

        doc.setId(1L);
        doc.setIdUser(instance.getId());
        doc.setName("Try");
        doc.setWallet(instance1);

        session = new MockHttpSession();
        session.setAttribute("user", instance);
        
        

        
    }

    @Test
    public void tcDeleteIsNull(){
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.delete(session, null).getStatusCode());
    }

    @Test
    public void tcDeleteIsNotnull(){
        session.setAttribute("user", instance);
        when(daodoc.findOne(1L)).thenReturn(doc);
        //when(daouse.update(instance)).thenReturn(null);
        assertEquals(HttpStatus.OK, controller.delete(session, doc.getId()).getStatusCode());
    }

   
}