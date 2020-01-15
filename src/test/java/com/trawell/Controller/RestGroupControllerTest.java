package com.trawell.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyObject;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.trawell.utilities.uploader.UploadUtils;
import com.trawell.controllers.AdController;
import com.trawell.controllers.RestGroupController;
import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.models.Photo;
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
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author Mario Paone
 */
@RunWith(MockitoJUnitRunner.class)
public class RestGroupControllerTest {

    @InjectMocks
    private RestGroupController controller;

    MockHttpSession session;
    @Mock
    TrawellGroupService groupDao;

    @Mock
    UserService userDao;
    
    @Mock
    WalletService walletDao;

    @Mock
    AdService adDao;

    @Mock
    AgencyService agencyDao;

    @Mock
    Model model;

    @Mock
    UploadUtils uploadUtils;
    Agency agency;
    User user;
    User user2;

    Ad ad;
    Collection<Ad> listaAd;

    @Mock
    TrawellGroup trawellGroup;

    TrawellGroup group;


    @Before
    public void init() {

        session = new MockHttpSession();
        user = new User();
        user.setId(1L);
        user.setName("Mario");
        user.setSurname("Rossi");
        user.setUsername("mariorossi");
        user.setMail("mariorossi@gmail.com");
        user.setPhone("3664422514");
        user.setPassword("B36912CFDBA2BDB8A055015FB817E79A");

        user2 = new User();
        user2.setId(2L);
        user2.setName("Luca");
        user2.setSurname("Pesce");
        user2.setUsername("lucapesce");
        user2.setMail("lucapesce@gmail.com");
        user2.setPhone("3664422514");
        user2.setPassword("B36912CFDBA2BDB8A055015FB817E79A");
        user2.setBanned(true);
        Wallet w = new Wallet();
        w.setGroup(group);
        w.setUser(user2);
        w.setId(2L);
        List<Wallet> listW = new ArrayList<Wallet>();
        listW.add(w);
        user2.setUserWallets(listW);

        group = new TrawellGroup();
        Set<User> partecipant = new HashSet<User>();
        partecipant.add(user);
        partecipant.add(user2);
        group.setParticipants(partecipant);
        group.setIdOwner(1L);

    }

    @Test
    public void addGroupWhenUserIsNull() {
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.addGroup(null, session).getStatusCode());

    }

    @Test
    public void addGroupWhenUserIsNotNull() {
        session.setAttribute("user", user);
        when(groupDao.create(any(TrawellGroup.class))).thenReturn(group);
        when(walletDao.create(any(Wallet.class))).thenReturn(null);
        assertEquals(HttpStatus.OK, controller.addGroup(new TrawellGroup(), session).getStatusCode());

    }

    @Test
    public void addMemberWhenUserIsNull() {
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.addMember(null,null, session).getStatusCode());

    }

    
    @Test
    public void addMemberWhenUserIsNotNull() {
        session.setAttribute("user", user);
        when(userDao.findByUsername(any(String.class))).thenReturn(user2);
        when(groupDao.findOne(any(Long.class))).thenReturn(group);
        when(groupDao.update(any(TrawellGroup.class))).thenReturn(group);
        assertEquals(HttpStatus.OK, controller.addMember("luca",3L, session).getStatusCode());

    }

    @Test
    public void removeMemberWhenUserIsNull() {
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.removeMember(null,null, session).getStatusCode());

    }

    
    /*@Test
    public void removeMemberWhenUserIsNotNull() {
        session.setAttribute("user", user);
        when(userDao.findOne(any(Long.class))).thenReturn(user2);
        when(groupDao.findOne(any(Long.class))).thenReturn(group);
        doNothing().when(walletDao).delete(null);
        assertEquals(HttpStatus.OK, controller.removeMember(5L,2L, session).getStatusCode());

    } */

    @Test
    public void eliminateGroupWhenUserIsNull() {
        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.eliminateGroup(null, session).getStatusCode());

    }

    @Test
    public void eliminateGroupWhenUserIsNotNull() {
        session.setAttribute("user", user);
        assertEquals(HttpStatus.OK, controller.eliminateGroup(1L, session).getStatusCode());

    }

}
