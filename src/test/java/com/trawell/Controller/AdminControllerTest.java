package com.trawell.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

import com.trawell.controllers.AdminController;
import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.models.User;
import com.trawell.services.AdService;
import com.trawell.services.AgencyService;
import com.trawell.services.BanDataService;
import com.trawell.services.UserService;
import com.trawell.utilities.email.EmailSenderService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.mockito.junit.MockitoJUnitRunner;
/**
 * @author Mario Paone
 */
@RunWith(MockitoJUnitRunner.class)
public class AdminControllerTest {

    @InjectMocks
    private AdminController controller;

    @Mock
    EmailSenderService emailService;

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

    @Before
    public void init() {
        user = new User();
        user.setId(1L);
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setUsername("mariorossi");
        user.setMail("mariorossi@gmail.com");
        user.setPhone("3664422514");
        user.setPassword("B36912CFDBA2BDB8A055015FB817E79A");
        user.setIsAdmin(true);

        session = new MockHttpSession();
        session.setAttribute("user", user);

        user2 = new User();
        user2.setId(2L);
        user2.setName("Luca");
        user2.setSurname("Pesce");
        user2.setUsername("lucapesce");
        user2.setMail("lucapesce@gmail.com");
        user2.setPhone("3664422514");
        user2.setPassword("B36912CFDBA2BDB8A055015FB817E79A");
        user2.setBanned(true);

        userList = new ArrayList<User>();
        userList.add(user);
        userList.add(user2);

        agency = new Agency();
        ad = new Ad();

    }

    @Test
    public void testLandingIsAdmin() {
        when(userDao.findAll()).thenReturn(userList);
        assertEquals("pages/admin/banusers", controller.landing(session, model));
    }

    @Test
    public void testLandingIsNotAdmin() {
        session.setAttribute("user", user2);
        assertEquals("pages/user/home", controller.landing(session, model));
    }

    @Test
    public void homeIsAdmin() {
        assertEquals("pages/admin/home", controller.home(session, model));
    }

    @Test
    public void homeIsNotAdmin() {
        session.setAttribute("user", user2);
        assertEquals("pages/user/login", controller.home(session, model));
    }

    @Test
    public void deleteAdsIsAdmin() {
        adList = new ArrayList<Ad>();
        agencyList = new ArrayList<Agency>();
        when(adDao.findAll()).thenReturn(adList);
        when(agencyDao.findAll()).thenReturn(agencyList);
        assertEquals("pages/admin/deletead", controller.deleteads(session, model));
    }

    @Test
    public void deleteAdsIsNotAdmin() {
        session.setAttribute("user", user2);
        assertEquals("pages/user/home", controller.deleteads(session, model));
    }

    @Test
    public void banUserIsAdminAndUserIsNotNull() throws UnsupportedEncodingException {
        when(userDao.findByUsername("lucapesce")).thenReturn(user2);
        assertEquals("pages/admin/home", controller.banuser("lucapesce","comportamento scorretto", java.sql.Date.valueOf("2020-01-01"), session, model));
        
    }

    @Test
    public void banUserIsNotAdmin() throws UnsupportedEncodingException {
        session.setAttribute("user", user2);
        assertEquals("pages/user/home", controller.banuser("lucapesce","comportamento scorretto", java.sql.Date.valueOf("2020-01-01"), session, model));
        
    }

    @Test
    public void banUserIsAdminAndUserIsNull() throws UnsupportedEncodingException {
        assertEquals("pages/admin/home", controller.banuser("lucapesce","comportamento scorretto", java.sql.Date.valueOf("2020-01-01"), session, model));
        
    }
}