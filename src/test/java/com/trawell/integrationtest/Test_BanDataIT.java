package com.trawell.integrationtest;

import com.trawell.controllers.RestUsersController;
import com.trawell.models.User;
import com.trawell.services.UserService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestUsersController.class)
public class Test_BanDataIT  {

    @Autowired
    private MockMvc mvc;

    MockHttpSession session;
    
    @MockBean
    private UserService service;

    User user;

    @Before public void init () {
        session = new MockHttpSession();
    }

    @Test
    public void tc_3_1_3_1 () throws Exception {

        String user = "";
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/users/login").session(session)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
}