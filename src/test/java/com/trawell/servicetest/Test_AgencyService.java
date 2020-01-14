package com.trawell.servicetest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.trawell.models.Agency;
import com.trawell.repositories.AgencyRepository;
import com.trawell.services.AgencyService;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class Test_AgencyService {
    @InjectMocks
    AgencyService dao;

    @Mock
    AgencyRepository repo;

    Agency newagency;
    Agency agency;
    @Before public void init(){
        Date date= new Date(2020, 2, 15);
        newagency = new Agency();
        agency= new Agency();
        agency.setId(1L);
        agency.setMail("agenziafasulla@gmail.com");
        agency.setNameAgency("Vendiamo faggioli");
        agency.setName("Daniele");
        agency.setSurname("Alemanno");
        agency.setPhone("3315155657");
        agency.setUsername("trawellstoler");
        agency.setBirth(date);
        agency.setPassword("92908C781853A92BE9A963319F18A3C5");
        agency.setUrl("www.tihofregato.it");
        agency.setVat("123456789012");

        newagency.setMail("notafakemail@gmail.com");
        newagency.setNameAgency("FAKE AGENT");
        newagency.setName("Silverio");
        newagency.setSurname("Ruggiero");
        newagency.setPhone("3375754849");
        newagency.setUsername("NotAnAgency");
        newagency.setBirth(date);
        newagency.setPassword("92908C781853A92BE9A963319F18A3C5");
        newagency.setUrl("www.inrealtasiamodisoccupati.com");
        newagency.setVat("987654321098");

    }


    @Test
    public void TC_ag(){

        when(repo.save(any(Agency.class))).thenReturn(agency);

        assertEquals(Long.valueOf(1L) ,dao.create(newagency).getId());
    }

    @Test
    public void TC_ag2(){
        assertEquals(null, dao.create(agency));
    }

    @Test
    public void TC_ag3(){
        Agency saveagency = new Agency();
        Date date= new Date(2020, 2, 15);
        saveagency.setId(1L);
        saveagency.setMail("agenziafasulla@gmail.com");
        saveagency.setNameAgency("Vendiamo faggioli");
        saveagency.setName("Daniele");
        saveagency.setSurname("Siffredi");
        saveagency.setPhone("3315155657");
        saveagency.setUsername("trawellstoler");
        saveagency.setBirth(date);
        saveagency.setPassword("92908C781853A92BE9A963319F18A3C5");
        saveagency.setUrl("www.tihofregato.it");
        saveagency.setVat("123456789012");

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.of(saveagency));
        when(repo.save(any(Agency.class))).thenReturn(agency);

        assertEquals(agency ,dao.update(agency));
    }

    @Test
    public void TC_ag4(){

        when(repo.findById(Long.valueOf(1L))).thenReturn(Optional.empty());

        assertEquals(null ,dao.update(agency));
    }

    @Test
    public void TC_ag5(){
        ArrayList<Agency> list = new ArrayList<>();
        list.add(agency);

        when(repo.findAll()).thenReturn(list);
        Collection<Agency> collection = dao.findAll();

        assertEquals(true, collection.size() == 1 && collection.contains(agency));
    }
    @Test
    public void TC_ag6(){
        when(repo.findById(1L)).thenReturn(Optional.of(agency));
        dao.delete(1L);
        Mockito.verify(repo, times(1)).delete(agency);
    }
}