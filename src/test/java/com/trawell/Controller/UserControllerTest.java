package com.trawell.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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



import java.sql.Date;
import java.util.ArrayList;



/**
 * @author Paolo Fasano
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {


    @Mock
    private CarsharingService daocarsharing;
    @Mock
    private ItineraryService daoitinerary;
    @Mock
    private PostService daopost;
    @Mock
    private UserService dao;

    ArrayList<Carsharing> amodello = new ArrayList<Carsharing>();
    TrawellGroup modello = new TrawellGroup();

    @InjectMocks
    UsersController controller = new UsersController();

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
    public void TestLoginPageNoUser() {
        session.setAttribute("user", null);
        assertEquals("pages/user/login", controller.loginPage(session));
    }

    @Test
    public void TestLoginPage() {
        
        assertEquals("pages/user/home", controller.loginPage(session));
    }

    @Test
    public void TestLogout() {
        
        assertEquals("redirect:/", controller.logout(session));
    }
   
    @Test
    public void TestSignUpPageNoUser() {
        session.setAttribute("user", null);
        assertEquals("pages/user/sign-Up", controller.signUpPage(session));
    }

    @Test
    public void TestSignUpPage() {
        
        assertEquals("pages/user/home", controller.signUpPage(session));
    }
        
    @Test
    public void TestLogin() {
        
       // when(dao.findByUsername(instance.getUsername())).thenReturn(instance);
        assertEquals("redirect:/", controller.login(instance.getUsername(), instance.getPassword(), session, model));
    }
    @Test
    public void TestSignUp() {

       // when(dao.doesUsernameExist("user")).thenReturn(false);
       // when(dao.doesEmailExist("mail")).thenReturn(false);
        session.setAttribute("user", null);
        assertEquals("redirect:/", controller.signUp(instance, session, model));
    }
    @Test
    public void TestSignLogged() {

        assertEquals("redirect:/", controller.signUp(instance, session, model));
    }
    @Test
    public void TestSignUpExist() {

        when(dao.doesUsernameExist("Username")).thenReturn(true);
      //  when(dao.doesEmailExist("mail")).thenReturn(true);
        session.setAttribute("user", null);
        assertEquals("pages/user/sign-Up", controller.signUp(instance, session, model));
    }
    
    /*
    @Test
    public void TestSignLoggedAgency() {
        Agency instance = new Agency();
        assertEquals("pages/user/home", controller.signUp(instance, session, model));
    }
    */

    
    
    @Test
    public void TestChangeData() {
        
        assertEquals("pages/user/modify-data", controller.changeData(session, model));
    }
    @Test
    public void TestChangeDataLogged() {
        session.setAttribute("user", null);
        assertEquals("pages/user/login", controller.changeData(session, model));
    }

    /**
     * @Test
    public void TestCheckBan() {
        BanDataService daoBan = new BanDataService();
        ArrayList<BanData> data = new ArrayList<BanData>();
        BanData ban = new BanData(0L, 0L, 0L, new Date(0, 0, 0), "motuivation");
        data.add(ban);
        when(daoBan.findAllByIdUser(0L)).thenReturn(data);
        assertEquals(false, controller.checkBan(instance));
    }
     */
    
    
    
}