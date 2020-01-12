package com.trawell;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import com.trawell.models.User;
import com.trawell.repositories.UserRepository;
import com.trawell.services.UserService;

import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class Test_ServiceUser {
    @InjectMocks
    UserService dao;
    
    @Mock
    UserRepository repo;

    @Test
    public void TC_1 () {
        User user = new User ();
        user.setName("Giuseppe");
        user.setSurname("Gesubaldo");
        user.setUsername("Vince");
        user.setMail("mariopoane@gmail.com");
        user.setPhone("3664422514");
        user.setPassword("92908C781853A92BE9A963319F18A3C5");

        User created = new User ();
        created.setId(1L);
        created.setName("Giuseppe");
        created.setSurname("Gesubaldo");
        created.setUsername("Vince");
        created.setMail("mariopoane@gmail.com");
        created.setPhone("3664422514");
        created.setPassword("92908C781853A92BE9A963319F18A3C5");

        when(repo.save(any(User.class))).thenReturn(created);

        assertEquals(Long.valueOf(1L) ,dao.create(user).getId());

    }
    
    @Test
    public void TC_2 () {
        User user = new User ();
        user.setId(1L);
        user.setName("Giuseppe");
        user.setSurname("Gesubaldo");
        user.setUsername("Vince");
        user.setMail("mariopoane@gmail.com");
        user.setPhone("3664422514");
        user.setPassword("92908C781853A92BE9A963319F18A3C5");

        User created = new User ();
        created.setId(1L);
        created.setName("Giuseppe");
        created.setSurname("Gesubaldo");
        created.setUsername("Vince");
        created.setMail("mariopoane@gmail.com");
        created.setPhone("3664422514");
        created.setPassword("92908C781853A92BE9A963319F18A3C5");

        assertEquals(null ,dao.create(user));

    }

    @Test
    public void TC_3 () {
        User updateUser = new User ();
        updateUser.setId(1L);
        updateUser.setName("Giuseppe");
        updateUser.setSurname("Gesubaldo");
        updateUser.setUsername("Vince");
        updateUser.setMail("mariopoane@gmail.com");
        updateUser.setPhone("3664422514");
        updateUser.setPassword("92908C781853A92BE9A963319F18A3C5");

        User persistedUser = new User();
        updateUser.setId(1L);
        updateUser.setName("Giuseppe");
        updateUser.setSurname("Gesubaldo");
        updateUser.setUsername("Vins");
        updateUser.setMail("mariopoane@gmail.com");
        updateUser.setPhone("3664422514");
        updateUser.setPassword("92908C781853A92BE9A963319F18A3C5");

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(persistedUser));
        when(repo.save(any(User.class))).thenReturn(updateUser);
        assertEquals(updateUser ,dao.update(updateUser));

    }

    @Test
    public void TC_4 () {
    
        User updateUser = new User ();
        updateUser.setId(1L);
        updateUser.setName("Giuseppe");
        updateUser.setSurname("Gesubaldo");
        updateUser.setUsername("Vince");
        updateUser.setMail("mariopoane@gmail.com");
        updateUser.setPhone("3664422514");
        updateUser.setPassword("92908C781853A92BE9A963319F18A3C5");

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.ofNullable(new User()));

        assertEquals(null ,dao.update(updateUser));

    }
}