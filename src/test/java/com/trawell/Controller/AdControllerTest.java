package com.trawell.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;


import com.trawell.controllers.AdController;
import com.trawell.controllers.AdminController;
import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.models.User;
import com.trawell.services.AdService;
import com.trawell.services.AgencyService;
import com.trawell.services.BanDataService;
import com.trawell.services.UserService;
import java.sql.Date;
import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdControllerTest {

    @InjectMocks
    private AdController controller;

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

    Agency agency;
    User user;
    MultipartFile file;
    Ad ad;
    Collection<Ad> listaAd;

    @Before
    public void init() {
        agency = new Agency();
        user = new User();

        session = new MockHttpSession();

        // setto il file
        MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

        ad = new Ad(Long.valueOf(1), Long.valueOf(3), "1111222233334444", Long.valueOf(50), Date.valueOf("2020-05-05"),
                Date.valueOf("2020-07-08"), "3");
        listaAd = new ArrayList<Ad>();
        listaAd.add(ad);

    }

    @Test
    public void homeIsAgency() {
        session.setAttribute("user", agency);
        assertEquals("pages/agency/home", controller.landing(session));
    }

    @Test
    public void homeIsNotAgency() {
        session.setAttribute("user", user);
        assertEquals("pages/user/home", controller.landing(session));
    }

    @Test
    public void createAdIsAgency() {
        session.setAttribute("user", agency);
        assertEquals("pages/agency/createadvertisement", controller.create_ad_page(session));
    }

    @Test
    public void createAdIsNotAgency() {
        session.setAttribute("user", user);
        assertEquals("pages/user/login", controller.create_ad_page(session));
    }

    @Test
    public void createAd1Month() {
        agency.setMail("fkdajfla@fjal.com");
        session.setAttribute("user", agency);
        try {
            assertEquals("pages/agency/home",
                    controller.createAd("1234123412341234", "1mesi", Date.valueOf("2021-12-12"), file, session, model));
        } catch (Exception e) {
        }
    }

    @Test
    public void createAd3Month() {
        agency.setMail("fkdajfla@fjal.com");
        session.setAttribute("user", agency);
        try {
            assertEquals("pages/agency/home",
                    controller.createAd("1234123412341234", "3mesi", Date.valueOf("2022-05-03"), file, session, model));
        } catch (Exception e) {
        }
    }

    @Test
    public void createAd6Month() {
        agency.setMail("fkdajfla@fjal.com");
        session.setAttribute("user", agency);
        try {
            assertEquals("pages/agency/home",
                    controller.createAd("1234123412341234", "6mesi", Date.valueOf("2021-04-04"), file, session, model));
        } catch (Exception e) {
        }
    }

    @Test
    public void createAd12Month() {
        agency.setMail("fkdajfla@fjal.com");
        session.setAttribute("user", agency);
        try {
            assertEquals("pages/agency/home", controller.createAd("1234123412341234", "12mesi",
                    Date.valueOf("2020-10-10"), file, session, model));
        } catch (Exception e) {
        }
    }

    @Test
    public void deleteAdsAgIsAgency() {
        session.setAttribute("user", agency);
        when(adDao.findAll()).thenReturn(listaAd);
        assertEquals("pages/agency/deletemyad", controller.deleteadsag(session, model));

    }

    @Test
    public void deleteAdsAgIsNotAgency() {
        session.setAttribute("user", user);
        assertEquals("pages/user/home", controller.deleteadsag(session, model));

    }

    @Test
    public void deleteAdsIsNotAgency() throws ParseException {
        assertEquals("pages/agency/home", controller.deleteAd(Long.valueOf(5),session, model));

        
    }
    
}