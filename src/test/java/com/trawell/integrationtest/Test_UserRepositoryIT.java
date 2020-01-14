package com.trawell.integrationtest;

import static org.junit.Assert.assertEquals;

import com.trawell.models.User;
import com.trawell.repositories.UserRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class Test_UserRepositoryIT {

    @Autowired
    private TestEntityManager em;
    @Autowired
    private UserRepository repo;
    
    User user;

    @Before public void init () {
        user = new User();
        String date = String.format("%d-%02d-%02d", 2019, 2, 21);

        user.setName("Giuseppe");
        user.setSurname("Gesubaldo");
        user.setUsername("Vince");
        user.setMail("mariopaone@gmail.com");
        user.setBirth(java.sql.Date.valueOf(date));
        user.setPhone("3664422514");
        user.setPassword("92908C781853A92BE9A963319F18A3C5");
        user.setBio("");
    }

    @Test
    public void tc_1 () throws Exception {
        User user = new User();
        String date = String.format("%d-%02d-%02d", 2019, 2, 21);

        user.setName("Giuseppe");
        user.setSurname("Gesubaldo");
        user.setUsername("Vince");
        user.setMail("mariopaone@gmail.com");
        user.setBirth(java.sql.Date.valueOf(date));
        user.setPhone("3664422514");
        user.setPassword("92908C781853A92BE9A963319F18A3C5");
        user.setBio("");

        em.persist(user);
        em.flush();
 
        // when
        User found = repo.findByUsername("Vince");
 
        // then
        assertEquals(found, user);
    }
}