package com.trawell.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import com.trawell.controllers.AdController;
import com.trawell.controllers.AdminController;
import com.trawell.controllers.WalletController;
import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.models.TrawellGroup;
import com.trawell.models.User;
import com.trawell.models.Wallet;
import com.trawell.services.AdService;
import com.trawell.services.AgencyService;
import com.trawell.services.BanDataService;
import com.trawell.services.TrawellGroupService;
import com.trawell.services.UserService;
import com.trawell.services.WalletService;

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
public class WalletControllerTest {

    @InjectMocks
    private WalletController controller;

    MockHttpSession session;
    @Mock
    WalletService walletDao;

    @Mock
    UserService userDao;

    @Mock
    TrawellGroupService trawellgroupDao;

    @Mock
    AgencyService agencyDao;

    @Mock
    Model model;

    Agency agency;
    User user;
    MultipartFile[] file;
    Ad ad;
    TrawellGroup trawellgroup;
    Collection<Ad> listaAd;
    

    @Before
    public void init() {
        session = new MockHttpSession();

        user = new User ();
        user.setId(1L);
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setUsername("mariorossi");
        user.setMail("mariorossi@gmail.com");
        user.setPhone("3664422514");
        user.setPassword("B36912CFDBA2BDB8A055015FB817E79A");


        trawellgroup = new TrawellGroup();
        trawellgroup.setId(4L);
        trawellgroup.setAllWallets(new ArrayList<Wallet>());
        
        //setto i file
        MockMultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
        

    }

    @Test
    public void viewAllDocument() {
        session.setAttribute("user", user);
        when(trawellgroupDao.findOne(4L)).thenReturn(trawellgroup);
        assertEquals("pages/user/walletGroup", controller.viewAllDocument(session, 4L, model));


    }

    
    @Test
    public void viewAllDocumentWhereGroupIsNull() {
        session.setAttribute("user", user);
        when(trawellgroupDao.findOne(4L)).thenReturn(null);
        assertEquals("pages/error", controller.viewAllDocument(session, 4L, model));


    }

    @Test
    public void addDocumentWhenUserIsNull() {
        session.setAttribute("user", null);
        assertEquals("pages/user/login", controller.addDocument(file,java.sql.Date.valueOf("2020-05-05"),session, model));


    }

    /*@Test
    public void addDocumentWhenUserIsNotNull() {
        session.setAttribute("user", user);
        session.setAttribute("idGroup", 2L);
        assertEquals("redirect:/Wallet/walletGroup?id=2", controller.addDocument(file,java.sql.Date.valueOf("2020-05-05"),session, model));


    }*/

    
}