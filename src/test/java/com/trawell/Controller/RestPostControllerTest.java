package com.trawell.Controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


import java.util.Collection;

import com.trawell.controllers.RestPostController;
import com.trawell.models.Ad;
import com.trawell.models.Agency;
import com.trawell.models.Post;
import com.trawell.models.User;
import com.trawell.services.AdService;
import com.trawell.services.AgencyService;
import com.trawell.services.BanDataService;
import com.trawell.services.PostService;
import com.trawell.services.UserService;
import com.trawell.utilities.email.EmailSenderService;

import java.io.UnsupportedEncodingException;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * @author Mario Paone
 */
@RunWith(MockitoJUnitRunner.class)
public class RestPostControllerTest {

    @InjectMocks
    private RestPostController controller;

    MockHttpSession session;
    @Mock
    BanDataService bandatadao;

    @Mock
    EmailSenderService emailService;

    @Mock
    UserService userDao;

    @Mock
    AdService adDao;

    @Mock
    PostService postDao;

    @Mock
    AgencyService agencyDao;

    @Mock
    Model model;

    Agency agency;
    User user;
    User user2;
    MultipartFile file;
    Ad ad;
    Collection<Ad> listaAd;
    Post post1;

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

        post1 = new Post();
    }

    @Test
    public void deletePostWhenUserIsNull() {
        session.setAttribute("user", null);
        when(postDao.findOne(1L)).thenReturn(new Post());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.deletePost(session, 1L).getStatusCode());

    }

    @Test
    public void deletePostWhenUserIsNotNullAndIdIsEqual() {

        session.setAttribute("user", user);
        Post post = new Post();
        post.setUser(user);
        when(postDao.findOne(1L)).thenReturn(post);
        assertEquals(HttpStatus.OK, controller.deletePost(session, 1L).getStatusCode());

    }

    @Test
    public void deletePostWhenUserIsAdmin() throws UnsupportedEncodingException {
        user.setIsAdmin(true);
        session.setAttribute("user", user);
        Post post = new Post();
        post.setUser(user2);
        doNothing().when(postDao).delete(1L);
        when(postDao.findOne(1L)).thenReturn(post);
        assertEquals(HttpStatus.OK, controller.deletePost(session,1L).getStatusCode());
        
       
        
    }

    @Test
    public void reportPostWhenUserIsNull() {

        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.reportPost(session, 1L).getStatusCode());

    }
    
    @Test
    public void reportPostWhenUserIsNotNull() {

        session.setAttribute("user", user);
        when(postDao.findOne(1L)).thenReturn(post1);
        when(postDao.update(post1)).thenReturn(post1);
        assertEquals(HttpStatus.OK, controller.reportPost(session, 1L).getStatusCode());

    }

    @Test
    public void discardPostWhenUserIsNull() {

        session.setAttribute("user", null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, controller.discardPost(session, 1L).getStatusCode());

    }

    @Test
    public void discardPostWhenUserIsNotNull() {

        session.setAttribute("user", user);
        when(postDao.findOne(1L)).thenReturn(post1);
        when(postDao.update(post1)).thenReturn(post1);
        assertEquals(HttpStatus.OK, controller.discardPost(session, 1L).getStatusCode());

    }
    
}