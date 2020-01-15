package com.trawell.Controller;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import com.trawell.controllers.CustomErrorController;
import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.models.User;
import com.trawell.services.AdService;
import com.trawell.services.AgencyService;
import com.trawell.services.BanDataService;
import com.trawell.services.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.mockito.junit.MockitoJUnitRunner;



@RunWith(MockitoJUnitRunner.class)
public class CustomErrorControllerTest {

    @InjectMocks
    private CustomErrorController controller;

    MockHttpSession session;
    @Mock
    BanDataService bandatadao;

    @Mock
    UserService userDao;

    @Mock 
    AdService adDao;

    @Mock
    AgencyService agencyDao;

    @Mock
    Model model;

    User user;
    User user2;
    Agency agency;
    Ad ad;
    Collection<User> userList;
    Collection<Ad> adList;
    Collection<Agency> agencyList;

    

    @Before public void init () {
        user = new User ();
        user.setId(1L);
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setUsername("mariorossi");
        user.setMail("mariorossi@gmail.com");
        user.setPhone("3664422514");
        user.setPassword("B36912CFDBA2BDB8A055015FB817E79A");

        session = new MockHttpSession();
     
        
    }


    @Test
    public void testHandleErrorUserIsNotNull() {
        session.setAttribute("user", user);
        assertEquals("error", controller.handleError(session).getViewName());
    }


    @Test
    public void testHandleErrorUserIsNull() {
        session.setAttribute("user", null);
        assertEquals("pages/user/login", controller.handleError(session).getViewName());
    }
    

   
}