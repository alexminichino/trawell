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
import org.springframework.web.multipart.MultipartFile;

import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Paolo Fasano
 */
@RunWith(MockitoJUnitRunner.class)
public class PostControllerTest {

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
    PostController controller = new PostController();

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

        modello.setId(0L);
        modello.setIsReported(false);
        modello.setPostDescription("description");
        modello.setGroup(new TrawellGroup());
        modello.setPhotos(photo);
        modello.setUser(instance);

        posts.add(modello); 
        
    }

    @After
    public void after() {
        System.out.println("After");
    }


    /**
     *  MultipartFile[] files;

    @Test
    public void TestCreatePost() {
        Post post = new Post(0L, 0L, 0L, 0L, "postDescription");
        when(daopost.create(post)).thenReturn(post);
        assertEquals("redirect:/group/view?id=0", controller.createPost(modello.getPostDescription(), files, post, session, 0L));
    }
     */
   

    @Test
    public void TestLanding() {
        
        assertEquals("pages/post/addPost", controller.landing());
    }

    @Test
    public void TestViewPosts() {
        
        assertEquals("redirect:/", controller.viewPosts(session, model));
    }
    @Test
    public void TestViewPostsNoUser() {
        session.setAttribute("user", null);
        assertEquals("pages/error", controller.viewPosts(session, model));
    }

    @Test
    public void TestViewReportedPostNoUser() {
        session.setAttribute("user", null);
        assertEquals("pages/error", controller.viewReportedPost(session, model, 0L));
    }

    @Test
    public void TestViewReportedPost() {

        when(daopost.findOne(0L)).thenReturn(modello);
        assertEquals("pages/post/postView", controller.viewReportedPost(session, model, 0L));
    }

    @Test
    public void TestListReportedPostsNoUser() {

        session.setAttribute("user", null);
        assertEquals("pages/error", controller.listReportedPosts(session, model));
    }

    @Test
    public void TestListReportedPosts() {

        instance.setIsAdmin(true);
        session.setAttribute("user", instance);
        when(daopost.findReportedPosts()).thenReturn(posts);
        assertEquals("pages/post/listReportedPosts", controller.listReportedPosts(session, model));
    }


}