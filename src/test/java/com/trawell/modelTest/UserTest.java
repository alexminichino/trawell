package com.trawell.modelTest;

import static org.junit.Assert.assertEquals;

import java.sql.Date;

import com.trawell.models.*;

import org.junit.Test;

public class UserTest {

    @Test
    public void testSetGetName() {
        User instance = new  User();
        instance.setName("Name");
        assertEquals("Name", instance.getName());
    }

    @Test
    public void testSetBirth() {
        User instance = new  User();
        instance.setBirth(new Date(0, 0, 0));
        assertEquals(new Date(0, 0, 0), instance.getBirth());
    }

    @Test
    public void testSetGetBanned() {
        User instance = new  User();
        instance.setBanned(false);
        assertEquals(false, instance.getBanned());
    }

    @Test
    public void testSetGetPhone() {
        User instance = new  User();
        instance.setPhone("Phone");
        assertEquals("Phone", instance.getPhone());
    }

    @Test
    public void testSetIsAdmin() {
        User instance = new  User();
        instance.setIsAdmin(false);
        assertEquals(false, instance.getIsAdmin());
    }

    @Test
    public void testSetGetIsBanned() {
        User instance = new  User();
        instance.setIsBanned(false);
        assertEquals(false, instance.getIsBanned());
    }

    @Test
    public void testSetGetSurname() {
        User instance = new  User();
        instance.setSurname("Surname");
        assertEquals("Surname", instance.getSurname());
    }

    @Test
    public void testSetGetUsername() {
        User instance = new  User();
        instance.setUsername("Username");
        assertEquals("Username", instance.getUsername());
    }

    @Test
    public void testSetGetMail() {
        User instance = new  User();
        instance.setMail("Mail");
        assertEquals("Mail", instance.getMail());
    }

    @Test
    public void testSetGetPassword() {
        User instance = new  User();
        instance.setPassword("Password");
        assertEquals("Password", instance.getPassword());
    }

    @Test
    public void testHashcode() {
        User instance = new  User();
        assertEquals(31, instance.hashCode());
    }

    @Test
    public void testToString()
    {
        User instance = new  User();
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
        String answere = "{ id='null', mail='Mail', username='Username', password='Password', name='Name', surname='Surname', birth='1899-12-31', bio='null', profilePhoto='0', phone='Phone', isAdmin='false', isBanned='false'}";
        assertEquals(answere, instance.toString());
    }

    }