package com.trawell;

import com.trawell.controllers.RestUsersController;
import com.trawell.controllers.UsersController;
import com.trawell.models.User;
import com.trawell.services.UserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@WebMvcTest(RestUsersController.class)
public class Test_ModificaDati  {

    @Autowired
    private MockMvc mvc;
 
    @MockBean
    private UserService service;

    @Test
    public void tc_3_1_3_1 () throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"£$%gbh\", \"Password\": \"frossi98\", \"Username\": \"Filippo98\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
    @Test
    public void tc_3_1_3_2 () throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"frossi98\", \"Username\": \"Filippo98\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isOk());  
    }
    @Test
    public void tc_3_1_3_3 () throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"\", \"Username\": \"Filippo98\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
    @Test
    public void tc_3_1_3_4() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678!\", \"Username\": \"Filippo98\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
    @Test
    public void tc_3_1_3_5() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678\", \"Username\": \"Filippo98\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isOk());  
    }
    @Test
    public void tc_3_1_3_6() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678\", \"Username\": \"\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
    @Test
    public void tc_3_1_3_7() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678\", \"Username\": \"Filippo04191298\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
    @Test
    public void tc_3_1_3_8() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678\", \"Username\": \"Filippo98!\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
    @Test
    public void tc_3_1_3_9() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678\", \"Username\": \"Filippo98\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
    @Test
    public void tc_3_1_3_10() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678\", \"Username\": \"Filippo1998\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isOk());  
    }
    @Test
    public void tc_3_1_3_11() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678\", \"Username\": \"Filippo1998\",\"Photo\":\"Img.doc\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isBadRequest());  
    }
    @Test
    public void tc_3_1_3_12() throws Exception {
        //da modificare la stringa json...
        //costruire eventuale json da mandare
        String user = "{\"bio\": \"Mi chiamo Filippo…\", \"Password\": \"12345678\", \"Username\": \"Filippo1998\",\"Photo\":\"Img.jpeg\"}";
        //da capire cosa mockare in questo caso
        //mockare il metodo richiamato dal rest controller e dire cosa passare in quel caso
        when(service.update(new User())).thenReturn(null);

        //simula richiesta a rest controller
        mvc.perform(post("/api/users/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(user))
        .andExpect(status().isOk());
    }
}